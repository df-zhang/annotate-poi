package me.dfzhang.excel.model;

import java.io.File;
import java.io.OutputStream;

/**
 * @ClassName ExportModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:24:24
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public interface ExportModel {
	void as(File file);

	void as(OutputStream out);
}
