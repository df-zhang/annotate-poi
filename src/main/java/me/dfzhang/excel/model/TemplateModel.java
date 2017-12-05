package me.dfzhang.excel.model;

import me.dfzhang.excel.model.impl.InfoModel;

/**
 * @ClassName Templatable
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:20:44
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public interface TemplateModel extends Model{

	InfoModel infoModel();

	ImportModel importModel();
	
	EditModel editModel();
}
