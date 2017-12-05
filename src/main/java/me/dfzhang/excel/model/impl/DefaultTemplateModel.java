package me.dfzhang.excel.model.impl;

import javax.sound.midi.MidiDevice.Info;

import me.dfzhang.excel.model.EditModel;
import me.dfzhang.excel.model.ImportModel;
import me.dfzhang.excel.model.TemplateModel;

/**
 * @ClassName ExcelTemplate
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:17:58
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public abstract class DefaultTemplateModel implements TemplateModel {
	private ImportModel importModel = new DefaultImportModel();
	protected InfoModel infoModel;
	protected EditModel editModel;

	@Override
	public ImportModel importModel() {
		return importModel;
	}

	@Override
	public InfoModel infoModel() {
		return infoModel;
	}

	@Override
	public EditModel editModel() {
		return editModel;
	}
}
