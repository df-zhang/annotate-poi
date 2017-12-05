package me.dfzhang.excel.style;

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
	XLS("xls"), XLSX("xlsx");

	private String value;

	private ExcelType(String value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

}
