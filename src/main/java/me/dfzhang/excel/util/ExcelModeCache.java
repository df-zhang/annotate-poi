package me.dfzhang.excel.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import me.dfzhang.excel.ExcelModel;

public class ExcelModeCache {
	private final static ConcurrentMap<String, ExcelModel> CACHE = new ConcurrentHashMap<>();

	public static ExcelModel get(String key) {
		return CACHE.get(key);
	}

	public static ExcelModel putOrReplace(String key, ExcelModel excelModel) {
		if (CACHE.containsKey(key)) {
			return CACHE.replace(key, excelModel);
		} else {
			ExcelModel old = CACHE.putIfAbsent(key, excelModel);
			if (old != null) {
				return CACHE.replace(key, excelModel);
			}
			return null;
		}
	}
}