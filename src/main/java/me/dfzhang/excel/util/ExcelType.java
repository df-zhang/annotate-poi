package me.dfzhang.excel.util;

/**
 * @ClassName com.horige.excel.style.FileType
 * 
 * @Version v1.0
 * @Date 2017年12月3日 下午10:38:17
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public enum ExcelType {
	XLS("xls", 256, 65536), XLSX("xlsx", 16384, 1048576);

	private String sufix;
	private int maxColumn;
	private int maxRow;

	private ExcelType(String sufix, int maxColumn, int maxRow) {
		this.sufix = sufix;
		this.maxColumn = maxColumn;
		this.maxRow = maxRow;
	}

	/**
	 * @return sufix
	 */
	public String getSufix() {
		return sufix;
	}

	/**
	 * @return maxColumn
	 */
	public int getMaxColumn() {
		return maxColumn;
	}

	/**
	 * @return maxRow
	 */
	public int getMaxRow() {
		return maxRow;
	}
}
