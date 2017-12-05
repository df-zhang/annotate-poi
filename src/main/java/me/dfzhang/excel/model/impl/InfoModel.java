package me.dfzhang.excel.model.impl;

import java.util.Date;

import me.dfzhang.excel.style.ExcelType;

/**
 * @ClassName com.horige.excel.model.DefaultInfoModel
 * 
 * @Version v1.0
 * @Date 2017年12月3日 下午11:22:28
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class InfoModel {
	String name;
	String author;
	ExcelType type;
	String tempDir;
	Date createTime;
	Date modifyTime;

	public InfoModel setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public InfoModel setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public InfoModel setType(ExcelType type) {
		this.type = type;
		return this;
	}

	public ExcelType getType() {
		return type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public InfoModel setTempDir(String tempDir) {
		this.tempDir = tempDir;
		return this;
	}


}
