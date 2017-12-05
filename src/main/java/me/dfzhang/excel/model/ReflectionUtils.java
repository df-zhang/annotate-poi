package me.dfzhang.excel.model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @ClassName poi.ReflectionUtils
 * 
 * @Version v1.0
 * @Date 2017年11月30日 下午10:21:27
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ReflectionUtils {
	@SuppressWarnings("unchecked")
	public static <T> T findAnnotataion(AnnotatedElement ele, Class<T> clz) {
		Annotation[] source = ele.getAnnotations();
		if (source == null || source.length < 1)
			return null;
		for (Annotation annotation : source) {
			if (annotation.annotationType() == clz) {
				return (T) annotation;
			}
		}
		return null;
	}


	public static Map<String, String> describe(Object object) {
		try {
			return BeanUtils.describe(object);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method[] getReadMethods(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		if (fields == null || fields.length < 1) {
			return new Method[0];
		}
		try {
			PropertyDescriptor descriptor = null;
			Method readMethod = null;
			for (Field field : fields) {
				descriptor = new PropertyDescriptor(field.getName(), clz);
				if ((readMethod = descriptor.getReadMethod()) != null) {

				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
