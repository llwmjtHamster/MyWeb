package com.myweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {

	public static String getClassName(Object bean) {
		try {

			Class clz = bean.getClass();

			String clzStr = clz.toString();

			// 得到类名

			String beanName = clzStr.substring(clzStr.lastIndexOf(".") + 1)
					.toLowerCase();

			return beanName;

		} catch (Exception e) {

			e.printStackTrace();

			return "";

		}

	}

	public static List<String> getFields(Object bean) {

		List<String> fieldList = new ArrayList<String>();
		try {

			Class clz = bean.getClass();

			Field[] strs = clz.getDeclaredFields();
		 
			for (int i = 0; i < strs.length; i++) {

				String field = strs[i].getName().toString();

				fieldList.add(field);

			}

			return fieldList;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	public static List<String> getValues2String(Object bean) {

		List<String> valueList = new ArrayList<String>();
		try {

			Class clz = bean.getClass();

			Field[] strs = clz.getDeclaredFields();

			for (Field f : strs) {
				if (f.getModifiers() > 2) {
					continue;
				}
				valueList.add(String.valueOf(f.get(bean)));
			}

			return valueList;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	public static Hashtable getFieldsAndTypes(Object bean) {

		Hashtable valueList = new Hashtable();
		try {

			Class clz = bean.getClass();

			Field[] strs = clz.getDeclaredFields();
 
			for (int i = 0; i < strs.length; i++) {

				String field = strs[i].getName().toString();

				String protype = strs[i].getType().toString();

				valueList.put(field, protype);

			}

			return valueList;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

}
