package me.dfzhang.excel.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.dfzhang.excel.model.ExcelHeader;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName DefaultHeaderModel
 * 
 * @Version v1.0
 * @Date 2017年12月5日 下午10:20:12
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class DefaultHeaderModel {
	private Map<String, ExcelHeader> keyMappedheaders;
	private List<ExcelHeader> columnHeaders;
	private DefaultRowModel rowModel;
	private SheetModel sheetModel;
	private int count;

	public DefaultHeaderModel() {
		rowModel = new DefaultRowModel();
		keyMappedheaders = new HashMap<>();
		columnHeaders = new ArrayList<>();
	}

	public DefaultHeaderModel setHeader(int column, String value) {
		columnHeaders.add(column, new ExcelHeader(column, null, value));
		return null;
	}

	public DefaultHeaderModel setHeader(int column, String key, String value) {
		ExcelHeader header = new ExcelHeader(column, key, value);
		columnHeaders.add(column, header);
		keyMappedheaders.put(key, header);
		return this;
	}

	public DefaultHeaderModel addHeader(String value) {
		columnHeaders.add(new ExcelHeader(++count, null, value));
		return this;
	}

	public DefaultHeaderModel addHeader(String key, String value) {
		if (!keyMappedheaders.containsKey(key)) {
			ExcelHeader header = new ExcelHeader(++count, key, value);
			columnHeaders.add(count, header);
			keyMappedheaders.put(key, header);
		}
		return this;
	}

	public DefaultHeaderModel addHeader(ExcelHeader header) {
		header.setIndex(++count);
		columnHeaders.add(count, header);
		String key = header.getKey();
		if (key != null && !"".equals(key)) {
			keyMappedheaders.put(key, header);
		}
		return this;
	}

	public DefaultHeaderModel addHeaders(Set<ExcelHeader> headers) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExcelHeader getHeader(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExcelHeader getHeader(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<ExcelHeader> getHeaders() {
		return new HashSet<>();
	}

	public DefaultRowModel getRow() {
		return rowModel;
	}

	public SheetModel getSheet() {
		return sheetModel;
	}

	DefaultHeaderModel setSheet(SheetModel sheetModel) {
		this.sheetModel = sheetModel;
		return this;
	}

	void build() {
		
	}

}
