package me.dfzhang.excel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName ExcelResolver
 * 
 * @Version v1.0
 * @Date 2017年12月7日 下午11:34:13
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */

public abstract class ExcelResolver {
	protected Class<?> tClass;

	@SuppressWarnings("unchecked")
	public ExcelResolver(Class<?> tClass) {
		System.out.println(tClass);
	}

	public <T> ExcelModel<T> resolve() {
		return new ExcelModel<>(resolveInfoModel(), resolveSheetInfo(), resolveHeaders());
	}

	protected abstract InfoModel resolveInfoModel();

	protected abstract List<SheetModel> resolveSheetInfo();

	protected abstract List<HeaderModel> resolveHeaders();

	protected Class<T> getTClass() {
		return tClass;
	}
}
