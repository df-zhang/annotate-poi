package me.dfzhang.excel.model;

import java.util.List;

/**
 * @ClassName CellModel
 * 
 * @Version v1.0
 * @Date 2017年12月10日 下午6:17:08
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class CellModel implements Comparable<CellModel> {
	private int column;
	private String key;
	private String header;
	private StyleModel style;

	private Class<?> valueClass;
	private boolean multiRow;
	private List<CellModel> multiRowCells;

	public CellModel(int column, String value) {
		this(column, null, value);
	}

	public CellModel(String key, String value) {
		this(0, key, value);
	}

	public CellModel(int column, String key, String header) {
		this.column = column;
		this.key = key;
		this.header = header;
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
	 * @return header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return style
	 */
	public StyleModel getStyle() {
		return style;
	}

	/**
	 * @return valueClass
	 */
	public Class<?> getValueClass() {
		return valueClass;
	}

	/**
	 * @return multiRow
	 */
	public boolean isMultiRow() {
		return multiRow;
	}

	/**
	 * @return multiRowCells
	 */
	public List<CellModel> getMultiRowCells() {
		return multiRowCells;
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
	 * @param header 属性赋值 header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @param style 属性赋值 style
	 */
	public void setStyle(StyleModel style) {
		this.style = style;
	}

	/**
	 * @param valueClass 属性赋值 valueClass
	 */
	public void setValueClass(Class<?> valueClass) {
		this.valueClass = valueClass;
	}

	/**
	 * @param multiRow 属性赋值 multiRow
	 */
	public void setMultiRow(boolean multiRow) {
		this.multiRow = multiRow;
	}

	/**
	 * @param multiRowCells 属性赋值 multiRowCells
	 */
	public void setMultiRowCells(List<CellModel> multiRowCells) {
		this.multiRowCells = multiRowCells;
	}

	@Override
	public int compareTo(CellModel o) {
		if (o == null) {
			return -1;
		}
		return column - o.column;
	}
}
