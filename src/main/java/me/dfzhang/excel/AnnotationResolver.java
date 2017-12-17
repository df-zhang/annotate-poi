package me.dfzhang.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.dfzhang.excel.annotation.ExcelCell;
import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.annotation.Sheet;
import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.util.ExcelType;
import me.dfzhang.excel.util.ReflectUtils;

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
public final class AnnotationResolver extends CachedResolver {
	private ExcelTemplate excelTemplate;

	public AnnotationResolver(Class<?> tempClass) {
		super(tempClass);
		excelTemplate = tempClass.getAnnotation(ExcelTemplate.class);
		if (excelTemplate == null) {
			throw new IllegalArgumentException(tempClass + " not a excel template");
		}
	}

	protected InfoModel resolveInfoModel() {
		InfoModel infoModel = new InfoModel();
		if (excelTemplate == null) {
			infoModel.setType(ExcelType.XLSX);
		} else {
			infoModel.setName(excelTemplate.name());
			infoModel.setType(excelTemplate.type());
		}
		return infoModel;
	}

	protected List<SheetModel> resolveSheets() {
		Sheet[] sheets = excelTemplate.sheet();
		List<SheetModel> sheetInfos = new ArrayList<>(sheets.length);
		for (Sheet sheet : sheets) {
			sheetInfos.add(new SheetModel(sheet.name(), sheet.page(), sheet.size()));
		}
		return sheetInfos;
	}

	protected List<CellModel> resolveHeaders() {
		Class<?> tempClass = getTempletClass();
		List<CellModel> cellModels = new ArrayList<>();
		Set<Field> fields = ReflectUtils.getFields(tempClass);
		try {
			PropertyDescriptor descriptor = null;
			Method readMethod = null;
			ExcelCell excelCell = null;
			String filedName;
			CellModel cellModel = null;
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				filedName = field.getName();
				excelCell = field.getAnnotation(ExcelCell.class);
				if (excelCell == null) {
					descriptor = new PropertyDescriptor(filedName, tempClass);
					if ((readMethod = descriptor.getReadMethod()) != null) {
						excelCell = readMethod.getAnnotation(ExcelCell.class);
					}
				}

				if (excelCell == null) {
					continue;
				}

				cellModel = new CellModel(excelCell.column(), filedName, excelCell.header());
				cellModels.add(cellModel);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return cellModels;
	}

	@Override
	protected String genKey() {
		return getTempletClass() + "@";
	}
}
