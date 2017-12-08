package me.dfzhang.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import me.dfzhang.excel.annotation.ExcelCell;
import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.annotation.Sheet;
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
public class AnnotationResolver extends ExcelResolver {
	protected ExcelTemplate excelTemplate;

	public AnnotationResolver(Class<?> tClass) {
		excelTemplate = tClass.getAnnotation(ExcelTemplate.class);
		if (excelTemplate == null) {
			throw new IllegalArgumentException(tClass + " not a excel template");
		}
	}

	public static void main(String[] args) {
		AnnotationResolver resolver = new AnnotationResolver();
		resolver.resolve();
	}

	@Override
	public <T> ExcelModel<T> resolve() {
		return new ExcelModel<T>(resolveInfoModel(), resolveSheetInfo(), resolveHeaders());
	}

	protected InfoModel resolveInfoModel() {
		InfoModel infoModel = new InfoModel();
		if (excelTemplate == null) {
			infoModel.setName(getTClass().getSimpleName());
			infoModel.setType(ExcelType.XLS);
		} else {
			infoModel.setName(excelTemplate.name());
			infoModel.setType(excelTemplate.type());
		}
		return infoModel;
	}

	protected List<SheetModel> resolveSheetInfo() {
		Sheet[] sheets = excelTemplate.sheet();
		List<SheetModel> sheetInfos = new ArrayList<>(sheets.length);
		for (Sheet sheet : sheets) {
			sheetInfos.add(new SheetModel(sheet.name(), sheet.page(), sheet.size()));
		}
		return sheetInfos;
	}

	protected List<HeaderModel> resolveHeaders() {
		List<HeaderModel> headers = new ArrayList<>();
		Field[] fields = getTClass().getDeclaredFields();
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
				descriptor = new PropertyDescriptor(filedName, getTClass());
				if ((readMethod = descriptor.getReadMethod()) != null) {
					if (excelCell == null) {
						excelCell = readMethod.getAnnotation(ExcelCell.class);
					}
				}
				if (excelCell == null) {
					continue;
				}
				header = new HeaderModel(excelCell.column(), filedName, excelCell.header());
				headers.add(header);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return headers;
	}
}
