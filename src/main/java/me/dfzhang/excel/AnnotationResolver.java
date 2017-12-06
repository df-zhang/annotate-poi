package me.dfzhang.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dfzhang.excel.annotation.ExcelCell;
import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.style.ExcelType;

/**
 * @ClassName AnnotationResolver
 * 
 * @Version v1.0
 * @Date 2017年12月6日 下午11:05:29
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class AnnotationResolver {
	public InfoModel parseBaseInfo(Class<?> templateClass) {
		InfoModel infoModel = new InfoModel();
		if (templateClass == null) {
			infoModel.setName(System.nanoTime() + "");
			infoModel.setType(ExcelType.XLS);
			return infoModel;
		}
		ExcelTemplate excelTemplate = templateClass.getAnnotation(ExcelTemplate.class);
		if (excelTemplate == null) {
			infoModel.setName(templateClass.getSimpleName());
			infoModel.setType(ExcelType.XLS);
		} else {
			infoModel.setName(excelTemplate.name());
			infoModel.setType(excelTemplate.type());
		}
		return infoModel;
	}

	public List<SheetModel> parseSheetInfo(Class<?> templateClass) {
		List<SheetModel> sheetInfos = null;
		if (templateClass == null) {
			sheetInfos = new ArrayList<>(1);
			sheetInfos.add(new SheetModel("sheet1"));
			return sheetInfos;
		}
		ExcelTemplate excelTemplate = templateClass.getAnnotation(ExcelTemplate.class);
		me.dfzhang.excel.annotation.Sheet[] sheets = excelTemplate.sheet();
		if (sheets.length > 0) {
			sheetInfos = new ArrayList<>(sheets.length);
			for (me.dfzhang.excel.annotation.Sheet sheet : sheets) {
				sheetInfos.add(new SheetModel(sheet.name(), sheet.page(), sheet.size()));
			}
		}
		return sheetInfos;
	}

	public Map<String, HeaderModel> parseHeader(Class<?> templateClass) {
		Map<String, HeaderModel> headers = new HashMap<>();
		if (templateClass == null) {
			return headers;
		}
		Field[] fields = templateClass.getDeclaredFields();
		if (fields == null || fields.length < 1) {
			return headers;
		}
		try {
			PropertyDescriptor descriptor = null;
			Method readMethod = null;
			ExcelCell excelCell = null;
			String filedName;
			HeaderModel header = null;
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				filedName = field.getName();
				excelCell = field.getAnnotation(ExcelCell.class);
				descriptor = new PropertyDescriptor(filedName, templateClass);
				if ((readMethod = descriptor.getReadMethod()) != null) {
					if (excelCell == null) {
						excelCell = readMethod.getAnnotation(ExcelCell.class);
					}
				}
				if (excelCell == null) {
					continue;
				}
				header = new HeaderModel(excelCell.column(), filedName, excelCell.header());
				headers.put(filedName, header);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return headers;
	}
}
