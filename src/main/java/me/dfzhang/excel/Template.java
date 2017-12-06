package me.dfzhang.excel;

import java.util.List;

import me.dfzhang.excel.annotation.ExcelTemplate;
import me.dfzhang.excel.model.HeaderModel;
import me.dfzhang.excel.model.InfoModel;

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
	public abstract List<HeaderModel> headers();
	
	public abstract InfoModel info();
	
	
}
