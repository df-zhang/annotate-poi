package me.dfzhang.excel.model;

/**
 * @ClassName DefaultSheetModel
 * 
 * @Version v1.0
 * @Date 2017年12月5日 下午10:10:03
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public interface SheetModel extends Model {
	HeaderModel header();

	RowModel row();
}
