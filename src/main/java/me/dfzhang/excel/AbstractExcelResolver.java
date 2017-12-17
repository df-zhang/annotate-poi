package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName CachedResolver
 * 
 * @Version v1.0
 * @Date 2017年12月7日 下午11:34:13
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */

public abstract class AbstractExcelResolver implements ExcelResolver {
	private Class<?> tempClass;

	public AbstractExcelResolver(Class<?> tempClass) {
		this.tempClass = tempClass;
	}

	public ExcelModel resolve() {
		return new ExcelModel(resolveInfoModel(), resolveSheets(), resolveHeaders());
	}

	protected Class<?> getTempletClass() {
		return tempClass;
	}

	protected abstract InfoModel resolveInfoModel();

	protected abstract List<SheetModel> resolveSheets();

	protected abstract List<CellModel> resolveHeaders();
}
