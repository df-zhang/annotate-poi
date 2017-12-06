package me.dfzhang.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName com.horige.excel.annotation.Sheet
 * 
 * @Version v1.0
 * @Date 2017年12月1日 上午12:08:10
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface Sheet {
	int page() default 0;

	String name() default "sheet1";

	int size() default 65535;

	Style style() default @Style();
}
