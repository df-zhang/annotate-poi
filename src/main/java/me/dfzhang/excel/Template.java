package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName Template
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午1:05:30
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@ExcelTemplate
public abstract class Template {
	public Template() {
	}

	public abstract List<HeaderModel> headerModels();

	public abstract InfoModel infoModel();

	public abstract List<SheetModel> sheetModels();
}
