package me.dfzhang.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.dfzhang.excel.style.ExcelType;

/**
 * @ClassName ExcelTemplate
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:58:18
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ExcelTemplate {

	String name() default "";

	Sheet[] sheet() default @Sheet();

	ExcelType type() default ExcelType.XLS;

	Headers headers() default @Headers({ @Header });
}
