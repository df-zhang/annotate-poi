package me.dfzhang.excel;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.style.ExcelType;

/**
 * @ClassName com.horige.excel.ExcelBuilder
 * 
 * @Version v1.0
 * @Date 2017年12月1日 下午9:56:33
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ExcelModel<T> {
	Workbook workbook;
	List<SheetModel> sheetModels;
	List<HeaderModel> headerModels;
	InfoModel infoModel;

	Editor editor;

	ExcelModel(InfoModel infoModel, List<SheetModel> sheetModels, List<HeaderModel> headerModels) {
		this.editor = new Editor();
		this.infoModel = infoModel;
		this.headerModels = headerModels;
		this.sheetModels = sheetModels;
		Collections.sort(this.headerModels);
		int index = 0;
		for (HeaderModel headerModel : this.headerModels) {
			headerModel.setColumn(index++);
		}
		index = 0;
		for (SheetModel sheetModel : this.sheetModels) {
			sheetModel.setPage(index++);
		}
		Collections.sort(this.sheetModels);
		if (ExcelType.XLS == infoModel.getType()) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new SXSSFWorkbook(1000);
		}
	}

	public Editor editor() {
		return editor;
	}

	public class Editor {
		List<Sheet> sheets = new ArrayList<>();
		Sheet curSheet;
		SheetModel curSheetInfo;
		int startRow = 0;
		ExportModel exportModel;
		private boolean hasHeader;
		private int maxCellNum = headerModels.size();

		Editor() {
			exportModel = new ExportModel();
			sheets = new ArrayList<>(sheets.size());
			Sheet sheet;
			Row row = null;
			Cell cell = null;
			CellStyle cellStyle = null;
			for (SheetModel sheetModel : sheetModels) {
				sheet = workbook.createSheet(sheetModel.getName());
				if (headerModels.size() > 0) {
					row = curSheet.createRow(0);
					cellStyle = workbook.createCellStyle();
					cellStyle.setWrapText(true);
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					for (HeaderModel header : headerModels) {
						cell = row.createCell(header.getColumn());
						cell.setCellStyle(cellStyle);
						cell.setCellValue(header.getValue());
					}
				}
				sheets.add(sheet);
			}
			if (headerModels.size() > 0) {
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

		public Editor append(Collection<T> col) {
			List<Map<String, String>> datas = new ArrayList<>(col.size());
			Map<String, String> map = null;
			Row row = null;

			int fullStart = maxCellNum;
			for (T object : col) {
				// datas.add(map = ReflectUtils.describe(object));
				row = curSheet.createRow(startRow++);
				map.remove("class");
				for (HeaderModel header : headerModels) {
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

		void addRow(int rownum, T t) {
			// PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, beanClass, readMethodName, writeMethodName);
		}

		public ExportModel exporter() {
			return exportModel;
		}
	}

	public class ExportModel {

		ExportModel() {
		}

		public void asFile(File file) {
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
