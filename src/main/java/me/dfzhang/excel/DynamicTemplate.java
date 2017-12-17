package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;

/**
 * @ClassName DynamicTemplate
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午1:05:30
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public abstract class DynamicTemplate {
	public DynamicTemplate() {
	}

	public abstract List<CellModel> headerModels();

	public abstract InfoModel infoModel();

	public abstract List<SheetModel> sheetModels();
}
