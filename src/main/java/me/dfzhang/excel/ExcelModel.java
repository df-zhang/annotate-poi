package me.dfzhang.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.style.ExcelType;

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

	public ExcelModel(InfoModel infoModel, List<SheetModel> sheetModels, List<CellModel> cellModels) {

		this.infoModel = infoModel;
		this.cellModels = cellModels;
		this.sheetModels = sheetModels;
		Collections.sort(this.cellModels);
		Collections.sort(this.sheetModels);

		int index = 0;
		for (CellModel CellModel : this.cellModels) {
			CellModel.setColumn(index++);
		}

		index = 0;
		for (SheetModel sheetModel : this.sheetModels) {
			sheetModel.setPage(index++);
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

		public Editor useSheet(int num) {
			num--;
			curSheetInfo = sheetModels.get(num);
			curSheet = sheets.get(num);
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
			List<Map<String, String>> datas = new ArrayList<>(col.size());
			Map<String, String> map = null;
			Row row = null;

			int fullStart = maxCellNum;
			for (Object object : col) {
				// datas.add(map = ReflectUtils.describe(object));
				row = curSheet.createRow(startRow++);
				map.remove("class");
				for (CellModel header : cellModels) {
					row.createCell(header.getColumn()).setCellValue(map.get(header.getKey()));
					map.remove(header.getKey());
				}
				if (!map.isEmpty()) {
					fullStart = maxCellNum;
					for (Entry<String, String> entry : map.entrySet()) {
						row.createCell(fullStart++).setCellValue(entry.getValue());
					}
				}
			}
			return this;
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
					name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
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
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
