package me.dfzhang.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.util.ExcelType;
import me.dfzhang.excel.util.ReflectUtils;
	
/**
 * @ClassName ExcelModel
 * 
 * @Version v1.0
 * @Date 2017年12月10日 下午11:29:39
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ExcelModel {

	private List<SheetModel> sheetModels;
	private List<CellModel> cellModels;
	private InfoModel infoModel;
	private Map<String, SheetModel> sheetMap = new HashMap<>();
	private Map<String, CellModel> headerMap = new HashMap<>();

	public ExcelModel(InfoModel infoModel, List<SheetModel> sheetModels,
			List<CellModel> cellModels) {
		this.infoModel = infoModel;
		this.cellModels = cellModels;
		this.sheetModels = sheetModels;
		verify();
	}

	void verify() {
		Collections.sort(this.cellModels);
		Collections.sort(this.sheetModels);
		int index = 0;
		for (CellModel cellModel : this.cellModels) {
			cellModel.setColumn(index++);
			headerMap.put(cellModel.getKey(), cellModel);
		}
		if (this.cellModels.size() != headerMap.size()) {
			this.cellModels.clear();
			this.cellModels.addAll(headerMap.values());
		}
		index = 0;
		for (SheetModel sheetModel : this.sheetModels) {
			sheetModel.setPage(index++);
			sheetMap.put(sheetModel.getName(), sheetModel);
		}
		if (this.sheetModels.size() != sheetMap.size()) {
			this.sheetModels.clear();
			this.sheetModels.addAll(sheetMap.values());
		}
	}

	public Editor createEditor() {
		return new Editor();
	}

	public class Editor {
		Workbook workbook;
		List<Sheet> sheets = new ArrayList<>();
		Sheet curSheet;
		SheetModel curSheetInfo;
		int startRow = 0;
		ExportModel exportModel;
		private boolean hasHeader;
		private int maxCellNum = cellModels.size();

		Editor() {
			if (ExcelType.XLS == infoModel.getType()) {
				workbook = new HSSFWorkbook();
			} else {
				workbook = new SXSSFWorkbook(1000);
			}
			exportModel = new ExportModel(workbook);
			sheets = new ArrayList<>(sheets.size());
			Sheet sheet;
			Row row = null;
			Cell cell = null;
			CellStyle cellStyle = null;
			for (SheetModel sheetModel : sheetModels) {
				sheet = workbook.createSheet(sheetModel.getName());
				if (cellModels.size() > 0) {
					row = sheet.createRow(0);
					cellStyle = workbook.createCellStyle();
					cellStyle.setWrapText(true);
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					for (CellModel header : cellModels) {
						cell = row.createCell(header.getColumn());
						cell.setCellStyle(cellStyle);
						cell.setCellValue(header.getHeader());
					}
				}
				sheets.add(sheet);
			}
			if (cellModels.size() > 0) {
				hasHeader = true;
				startRow++;
			}

			curSheetInfo = sheetModels.get(0);
			curSheet = sheets.get(0);

		}

		/**
		 * @Methods useSheet
		 * 
		 * @param num start with 1
		 * @return
		 * 
		 * @Description TODO
		 */
		public Editor useSheet(int page) {
			page--;
			curSheetInfo = sheetModels.get(page);
			curSheet = sheets.get(page);
			startRow = curSheet.getLastRowNum();
			return this;
		}

		public Editor write(Collection<T> col) {
			if (hasHeader) {
				startRow = 1;
			} else {
				startRow = 0;
			}
			return append(col);
		}

		public Editor append(Collection<?> col) {
			for (Object object : col) {
				addRow(object);
			}
			return this;
		}

		Row createRow() {
			return curSheet.createRow(startRow++);
		}

		Cell createCell(Row row, int column, Object value, boolean isFormula) {
			Cell cell = row.createCell(column);
			if (value == null) {
				cell.setCellValue("");
			} else {
				if (isFormula) {
					cell.setCellFormula((String) value);
				} else {
					Class<?> valueClass = value.getClass();
					if (valueClass.isPrimitive()) {
						cell.setCellValue((double) value);
					} else if (Date.class.isInstance(value)) {
						cell.setCellValue((Date) value);
					} else if (Calendar.class.isInstance(value)) {
						cell.setCellValue((Calendar) value);
					} else if (RichTextString.class.isInstance(value)) {
						cell.setCellValue((RichTextString) value);
					} else {
						cell.setCellValue((String) value);
					}
				}
			}
			return cell;
		}

		void addRow(Object object) {
			if (object == null) {
				return;
			}
			Class<?> objClass = object.getClass();
			Row row = createRow();
			String fieldName = null;
			Method readMethod = null;
			CellModel header = null;
			int fullStart = maxCellNum;
			Set<Field> fields = ReflectUtils.getFields(object.getClass());
			Object value;
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					readMethod = ReflectUtils.getReaderMethod(objClass, field);
					value = ReflectUtils.getValue(object, readMethod);
					if (hasHeader) {
						header = headerMap.get(fieldName);
						if (header == null) {
							createCell(row, fullStart++, value, false);
						} else {
							createCell(row, header.getColumn(), value, false);
						}
					} else {
						createCell(row, fullStart++, value, false);
					}
				}
			}
		}

		public ExportModel exporter() {
			return exportModel;
		}
	}

	public class ExportModel {
		private Workbook workbook;

		ExportModel(Workbook workbook) {
			this.workbook = workbook;
		}

		public void as(File file) {
			if (!file.exists()) {
				int index = file.getName().indexOf('.');
				if (index < 0) {
					// 当作目录
					file.mkdirs();
				} else {
					file.getParentFile().mkdirs();
				}
			}
			if (file.isDirectory()) {
				String name = infoModel.getName();
				if (name == null || name.isEmpty()) {
					name = LocalDateTime.now()
							.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
				}
				file = new File(file, name + "." + infoModel.getSufix().toLowerCase());
			}
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file);
				workbook.write(out);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void as(OutputStream out) {
			try {
				workbook.write(out);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

}
