package me.dfzhang.excel.model;

/**
 * @ClassName HeaderModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 下午10:56:15
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class HeaderModel implements Comparable<HeaderModel> {
	private int column;
	private String key;
	private String value;

	public HeaderModel(int column, String value) {
		this(column, null, value);
	}

	public HeaderModel(String key, String value) {
		this(0, key, value);
	}

	public HeaderModel(int column, String key, String value) {
		this.column = column;
		this.key = key;
		this.value = value;
	}

	/**
	 * @return column
	 */
	public int getColumn() {
		return column;
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
	 * @param column 属性赋值 column
	 */
	public void setColumn(int column) {
		this.column = column;
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

	@Override
	public int compareTo(HeaderModel o) {
		if (o == null) {
			return -1;
		}
		return column - o.column;
	}
}
