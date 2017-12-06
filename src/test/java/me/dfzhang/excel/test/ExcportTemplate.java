package me.dfzhang.excel.test;

import java.util.List;

import me.dfzhang.excel.Template;
import me.dfzhang.excel.annotation.Columns;
import me.dfzhang.excel.annotation.ExcelCell;
import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;

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
@ExcelTemplate
public class ExcportTemplate extends Template {
	@ExcelCell(column = Columns.A)
	private String name;

	public static void main(String[] args) {
		
	}

	@Override
	public List<HeaderModel> headers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoModel info() {
		// TODO Auto-generated method stub
		return null;
	}
}
