package me.dfzhang.excel;

import me.dfzhang.excel.util.ExcelModeCache;

/**
 * @ClassName CachedResolver
 * 
 * @Version v1.0
 * @Date 2017年12月11日 下午10:32:33
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public abstract class CachedResolver extends AbstractExcelResolver {

	public CachedResolver(Class<?> tempClass) {
		super(tempClass);
	}

	@Override
	public ExcelModel resolve() {
		String key = genKey();
		ExcelModel excelModel = ExcelModeCache.get(key);
		if (excelModel == null) {
			excelModel = super.resolve();
			ExcelModeCache.putOrReplace(key, excelModel);
		}
		return excelModel;
	}

	protected abstract String genKey();
}
