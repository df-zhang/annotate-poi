package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName TemplateResolver
 * 
 * @Version v1.0
 * @Date 2017年12月7日 下午11:33:59
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class TemplateResolver<T extends Template> extends ExcelResolver<T> {
	private Template template;

	public TemplateResolver() {
		try {
			template = getTClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ExcelModel<T> resolve() {
		return new ExcelModel<>(template.infoModel(), template.sheetModels(), template.headerModels());
	}

	@Override
	protected InfoModel resolveInfoModel() {
		return template.infoModel();
	}

	@Override
	protected List<SheetModel> resolveSheetInfo() {
		return template.sheetModels();
	}

	@Override
	protected List<HeaderModel> resolveHeaders() {
		return template.headerModels();
	}

}
