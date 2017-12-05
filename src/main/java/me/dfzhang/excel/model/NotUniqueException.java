package me.dfzhang.excel.model;

/**
 * @ClassName NotUniqueException  
 * 
 * @Version v1.0
 * @Date 2017年12月5日 下午10:23:50 
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class NotUniqueException extends RuntimeException{
	
	public NotUniqueException() {
		
	}
	
	public NotUniqueException(String msg) {
		super(msg);
	}
}
