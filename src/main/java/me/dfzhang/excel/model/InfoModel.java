package me.dfzhang.excel.model;

import java.util.Date;

import me.dfzhang.excel.util.ExcelType;

/**
 * @ClassName InfoModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 下午10:56:26
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class InfoModel {
	private String name;
	private ExcelType type;
	private Date createDate;
	private Date modifyDate;
	private String sufix;

	public InfoModel() {
	}

	public InfoModel(String name) {
		this(name, ExcelType.XLS);
	}

	public InfoModel(String name, ExcelType type) {
		setName(name);
		setType(type);
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return type
	 */
	public ExcelType getType() {
		return type;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @return sufix
	 */
	public String getSufix() {
		return sufix;
	}

	/**
	 * @param name 属性赋值 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type 属性赋值 type
	 */
	public void setType(ExcelType type) {
		this.type = type;
		sufix = type.getSufix();
	}

}
