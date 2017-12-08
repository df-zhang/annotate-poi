package me.dfzhang.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ReflectUtils
 * 
 * @Version v1.0
 * @Date 2017年12月6日 下午11:11:28
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ReflectUtils {
	public static Set<Field> getFields(Class<?> cls) {
		Set<Field> results = new HashSet<>();
		Field[] fields = cls.getDeclaredFields();
		results.addAll(Arrays.asList(fields));
		if (cls.getSuperclass() != Object.class) {
			results.addAll(getFields(cls.getSuperclass()));
		}
		return results;
	}

	public static Field getField(Class<?> cls, String fieldName) {
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		if (field == null && cls.getSuperclass() != Object.class) {
			field = getField(cls.getSuperclass(), fieldName);
		}
		return field;
	}

	public static Object getValue(Object obj, String fieldName) {
		Field field = getField(obj.getClass(), fieldName);
		if (field != null) {
			try {
				PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, obj.getClass());
				return descriptor.getReadMethod().invoke(obj);
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
