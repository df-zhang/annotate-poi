package me.dfzhang.excel.model;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @ClassName ImportModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:23:49
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public interface ImportModel {
	EditModel from(File file);

	EditModel from(InputStream in);

	EditModel from(URL url);
}
