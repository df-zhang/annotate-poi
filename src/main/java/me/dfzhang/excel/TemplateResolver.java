package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.model.CellModel;
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
public class TemplateResolver<T extends DynamicTemplate> extends AbstractExcelResolver {
	private DynamicTemplate template;

	public TemplateResolver(DynamicTemplate template) {
		super(template.getClass());
		this.template = template;
	}

	@Override
	protected InfoModel resolveInfoModel() {
		return template.infoModel();
	}

	@Override
	protected List<SheetModel> resolveSheets() {
		return template.sheetModels();
	}

	@Override
	protected List<CellModel> resolveHeaders() {
		return template.headerModels();
	}

}
