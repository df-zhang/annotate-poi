package me.dfzhang.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ExcelCell
 * 
 * @Version v1.0
 * @Date 2017年12月6日 上午12:56:24
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ExcelCell {
	public static int i = 0;
	int column() default 0;

	String header() default "";

	Style style() default @Style();
}
