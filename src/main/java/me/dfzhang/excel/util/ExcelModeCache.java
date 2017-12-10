package me.dfzhang.excel.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import me.dfzhang.excel.ExcelModel;

public class ExcelModeCache {
	private final static ConcurrentMap<Class<?>, ExcelModel> CACHE = new ConcurrentHashMap<>();

	public static ExcelModel get(Class<?> cls) {
		return CACHE.get(cls);
	}

	public static ExcelModel putOrReplace(Class<?> cls, ExcelModel excelModel) {
		if (CACHE.containsKey(cls)) {
			return CACHE.replace(cls, excelModel);
		} else {
			ExcelModel old = CACHE.putIfAbsent(cls, excelModel);
			if (old != null) {
				return CACHE.replace(cls, excelModel);
			}
			return null;
		}

	}

}