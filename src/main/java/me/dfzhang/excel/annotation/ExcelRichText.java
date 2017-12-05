package me.dfzhang.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName com.horige.excel.annotation.RichText  
 * 
 * @Version v1.0
 * @Date 2017年12月3日 下午10:35:00 
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD })
public @interface ExcelRichText {

}
