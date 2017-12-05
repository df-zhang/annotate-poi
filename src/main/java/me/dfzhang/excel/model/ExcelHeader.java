package me.dfzhang.excel.model;

/**
 * @ClassName ExcelHeader  
 * 
 * @Version v1.0
 * @Date 2017年12月5日 下午10:20:58 
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ExcelHeader implements Comparable<ExcelHeader> {
	private int index = -1;
	private String key;
	private String value;

	public ExcelHeader() {
	}

	public ExcelHeader(int index, String key, String value) {
		this.index = index;
		this.key = key;
		if (value == null) {
			this.value = key;
		} else {
			this.value = value;
		}
	}

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param index 属性赋值 index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param key 属性赋值 key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param value 属性赋值 value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * @Methods compareTo
	 * 
	 * @param o
	 * 
	 * @return
	 * 
	 * @Description TODO
	 */
	@Override
	public int compareTo(ExcelHeader o) {
		if (o == null) {
			return 1;
		}
		if (index == 0)
			return 1;
		if (o.index == 0)
			return -1;
		
		if (index > o.index) {
			return 1;
		} else if (index < o.index) {
			return -1;
		}
		return 0;
	}
}
