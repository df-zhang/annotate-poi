package me.dfzhang.excel.test;

import java.util.List;

import me.dfzhang.excel.DynamicTemplate;
import me.dfzhang.excel.annotation.Columns;
import me.dfzhang.excel.annotation.ExcelCell;
import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.annotation.Sheet;
import me.dfzhang.excel.model.CellModel;
import me.dfzhang.excel.model.InfoModel;
import me.dfzhang.excel.model.SheetModel;
import me.dfzhang.excel.util.ExcelType;

/**
 * @ClassName ExcportTemplate
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午1:04:47
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@ExcelTemplate(name = "名字测试", type = ExcelType.XLSX, sheet = { @Sheet(name = "Page1"), @Sheet(name = "Page2"), @Sheet(name = "Page3") })
public class ExcportTemplate extends DynamicTemplate {
	@ExcelCell(column = Columns.A, header = "Your Name")
	private String name;

	@Override
	public List<CellModel> headerModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoModel infoModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SheetModel> sheetModels() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 属性赋值 name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
