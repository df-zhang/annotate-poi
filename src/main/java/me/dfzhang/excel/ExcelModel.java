package me.dfzhang.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

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
public class ExcelModel {
	Workbook workbook;
	List<Sheet> sheets = new ArrayList<>();

	Class<?> templateClass;
	InfoModel infoModel;

	Editor editor;

	public static ExcelModel template(Class<?> cls) {
		return new ExcelModel(cls);
	}

	private ExcelModel(Class<?> cls) {
		templateClass = cls;
	}

	public Editor editor() {
		return editor;
	}

	public class Editor {
		List<Sheet> sheets = new ArrayList<>();
		Sheet curSheet;
		SheetModel curSheetInfo;
		int startRow = 0;
		ExportModel exportModel = new ExportModel();

		Editor() {
			if (ExcelType.XLS == infoModel.getType()) {
				workbook = new HSSFWorkbook();
			} else {
				workbook = new SXSSFWorkbook(1000);
			}
			sheets = new ArrayList<>(sheets.size());
			curSheet = sheets.get(0);
			// curSheet = workbook.createSheet(curSheetInfo.getName());

//			if (headers.size() > 0) {
//				Row row = curSheet.createRow(startRow);
//				startRow++;
//				CellStyle cellStyle = workbook.createCellStyle();
//				cellStyle.setWrapText(true);
//				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
//				List<HeaderModel> HeaderModels = new ArrayList<>(headers.values());
//				resort(HeaderModels);
//
//				Cell cell = null;
//				int i = 0;
//				for (HeaderModel header : HeaderModels) {
//					cell = row.createCell(i++);
//					cell.setCellStyle(cellStyle);
//					cell.setCellValue(header.getValue());
//				}
//			}
		}

		public Editor useSheet(int num) {
			curSheet = sheets.get(num - 1);
			startRow = curSheet.getLastRowNum();
			return this;
		}

		public Editor write(Collection<?> col) {
//			if (headers.size() > 0) {
//				startRow = 1;
//			}
			return append(col);
		}

		public Editor append(Collection<?> col) {
//			List<Map<String, String>> datas = new ArrayList<>(col.size());
//			Map<String, String> map = null;
//			Row row = null;
//			Collection<HeaderModel> HeaderModels = headers.values();
//
//			int maxCell = HeaderModels.size();
//			int fullStart = maxCell;
//			for (Object object : col) {
//				datas.add(map = ReflectionUtils.describe(object));
//				row = curSheet.createRow(startRow++);
//				map.remove("class");
//				for (HeaderModel header : HeaderModels) {
//					row.createCell(header.getColumn()).setCellValue(map.get(header.getKey()));
//					map.remove(header.getKey());
//				}
//				if (!map.isEmpty()) {
//					fullStart = maxCell;
//					for (Entry<String, String> entry : map.entrySet()) {
//						row.createCell(fullStart++).setCellValue(entry.getValue());
//					}
//				}
//			}
			return this;
		}

		void resort(List<HeaderModel> headers) {
			Collections.sort(headers);
			int i = 0;
			for (HeaderModel header : headers) {
				header.setColumn(i++);
			}
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
