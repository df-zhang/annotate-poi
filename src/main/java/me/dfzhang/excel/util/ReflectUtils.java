package me.dfzhang.excel.util;

import static java.util.Locale.ENGLISH;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

	/**
	 * Returns a String which capitalizes the first letter of the string.
	 */
	public static String capitalize(String name) {
		if (name == null || name.length() == 0) {
			return name;
		}
		return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
	}

	public static void main(String[] args) {
	}

	@SuppressWarnings("rawtypes")
	public static String getClassName(Class type) {
		if (type.isArray()) {
			return getClassName(type.getComponentType());
		}
		String name = type.getName();
		return name.substring(name.lastIndexOf('.') + 1);
	}

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

	public static Method getReaderMethod(Class<?> cls, Field field) {
		String readMethodName = "get" + capitalize(field.getName());
		try {
			return cls.getMethod(readMethodName, field.getType());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getValue(Object obj, Method mthod) {
		if (obj != null) {
			try {
				return mthod.invoke(obj);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object getValue(Object obj, String fieldName) {
		Field field = getField(obj.getClass(), fieldName);
		if (field != null) {
			try {
				PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, obj.getClass());
				return descriptor.getReadMethod().invoke(obj);
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static PropertyDescriptor getDescriptor(Field field, Class<?> beanClass) {
		try {
			return new PropertyDescriptor(field.getName(), beanClass);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
