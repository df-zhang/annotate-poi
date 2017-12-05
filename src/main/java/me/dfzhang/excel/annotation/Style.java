package me.dfzhang.excel.annotation;

import me.dfzhang.excel.style.Border;
import me.dfzhang.excel.style.HorizontalAlignment;
import me.dfzhang.excel.style.VerticalAlignment;

/**
 * @ClassName com.horige.excel.annotation.Style
 * 
 * @Version v1.0
 * @Date 2017年12月3日 下午9:27:20
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public @interface Style {
	/**
	 * @Methods horizontal
	 * 
	 * @return
	 * 
	 * @Description 水平对齐
	 */
	HorizontalAlignment horizontal() default HorizontalAlignment.GENERAL;

	/**
	 * @Methods vertical
	 * 
	 * @return
	 * 
	 * @Description 垂直对齐
	 */
	VerticalAlignment vertical() default VerticalAlignment.CENTER;

	/**
	 * @Methods border
	 * 
	 * @return
	 * 
	 * @Description 边框
	 */
	Border border() default Border.NONE;

	/**
	 * @Methods wrap
	 * 
	 * @return
	 * 
	 * @Description 自动换行
	 */
	boolean wrap() default false;

	/**
	 * @Methods indent
	 * 
	 * @return
	 * 
	 * @Description 缩进
	 */
	short indent() default 0;

	/**
	 * @Methods rotation
	 * 
	 * @return
	 * 
	 * @Description 文本旋转 0~180
	 */
	short rotation() default 0;
}
