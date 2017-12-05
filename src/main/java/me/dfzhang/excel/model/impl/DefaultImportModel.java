package me.dfzhang.excel.model.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import me.dfzhang.excel.model.EditModel;
import me.dfzhang.excel.model.ImportModel;

/**
 * @ClassName DefaultImportModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:34:19
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class DefaultImportModel implements ImportModel {
	EditModel editor = new DefaultEditModel();
	@Override
	public EditModel from(File file) {
		return editor;
	}

	@Override
	public EditModel from(InputStream in) {
		return editor;
	}

	@Override
	public EditModel from(URL url) {
		return editor;
	}

}
