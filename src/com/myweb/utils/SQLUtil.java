package com.myweb.utils;

import java.io.Serializable;
import java.lang.reflect.Field;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.Hashtable;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
 

public class SQLUtil

{

	private final static String database_table_prefix = "database.table.prefix";

	public  static String pkColumnName = "SN";
	
	public static String tableName="";

	public SQLUtil()

	{

		// pkColumnName is vary of Table,so mothods which need pkColumn can not
		// use static

		this.pkColumnName = "SN";

	}

	public SQLUtil(String pkColumnName)

	{

		// pkColumnName is vary of Table,so mothods which need pkColumn can not
		// use static

		this.pkColumnName = pkColumnName;

	}

	/**
	 * 
	 * @param Object
	 *            the object for insert
	 * 
	 * @return an insert String like below:
	 * 
	 *         insert into vmm_ag(sn,domainname,modifydate,testDouble)
	 * 
	 *         values(5,'domainname5',to_date('20080121','YYYYMMDD'),22.4)
	 */

	public static String genInsertSQL(Object bean)

	{

		List<String> fields = BeanUtil.getFields(bean);

		List<String> values = BeanUtil.getValues2String(bean);

		Hashtable fieldsTypes = BeanUtil.getFieldsAndTypes(bean);

		// System.out.println("fields.size():"+fields.size()+"--values.size():"+values.size());

		if (fields.size() != values.size())

		{

			String error = "ERROR:genInsertSQL(bean)->fields's size does not match values' size,bean:"
					+ bean
					+

					"\n fields.size():"
					+ fields.size()
					+ "--values.size():"
					+ values.size();

			System.err.println(error);

			return error;

		}

		// get database table Name

	 
		String insertSQL = "insert into " + tableName + " (";

		for (int i = 0; i < fields.size(); i++)

		{

			// if value of this field is null,then ignore this field

			if (values.get(i) != null)

			{

				insertSQL += fields.get(i);

				// if not the last field,then add ","

				if (i != fields.size() - 1)

				{

					insertSQL += ",";

				}

			}

		}

		insertSQL += ")";

		insertSQL += "values (";

		for (int j = 0; j < fields.size(); j++)

		{

			String value = "";

			String typeOfThisField = (String) fieldsTypes.get(fields.get(j));

			if (typeOfThisField.equalsIgnoreCase(String.class.toString()))

			{ // when String ,add '' around the string ,like:'tempString'

				value = "'" + values.get(j) + "'";

			}

			else if (typeOfThisField.equalsIgnoreCase("Date")) { // select
																	// to_date('2008-01-01
																	// 14:26:38','YYYY-MM-DD
																	// HH24:MI:SS')
																	// from dual

				value = "to_date('" + (String) values.get(j)
						+ "','YYYY-MM-DD HH24:MI:SS')";

			}

			else

			{ // other types like long,double ,add nothing but their value

				value = (String) values.get(j);

			}

			insertSQL += value;

			if (j != fields.size() - 1)

			{

				insertSQL += ",";

			}

		}

		insertSQL += ")";

		// System.out.println("SQL is:"+insertSQL);

		return insertSQL;

	}

	public  static String genUpdateSQL(Object bean)

	{

		List<String> fields = BeanUtil.getFields(bean);

		List<String> values = BeanUtil.getValues2String(bean);

		Hashtable fieldsTypes = BeanUtil.getFieldsAndTypes(bean);

		if (fields.size() != values.size())

		{

			String error = "ERROR:genUpdateSQL(bean)->fields's size does not match values' size,bean:"
					+ bean
					+

					"\n fields.size():"
					+ fields.size()
					+ "--values.size():"
					+ values.size();

			System.err.println(error);

			return error;

		}

		// get database table Name

	 
		String updateSQL = "update " + tableName + " set ";

		String pkColumnValue = "";

		for (int i = 0; i < fields.size(); i++)

		{

			String setSQL = fields.get(i) + "=";

			String typeOfThisField = (String) fieldsTypes.get(fields.get(i));

			String value = "";

			if (typeOfThisField.equalsIgnoreCase(String.class.toString()))

			{ // when String ,add '' around the string ,like:'tempString'

				value = "'" + values.get(i) + "'";

				setSQL += value;

				if (pkColumnName.equalsIgnoreCase(fields.get(i)))

				{

					pkColumnValue = value;

				}

			}

			else if (typeOfThisField.equalsIgnoreCase("Date"))

			{ // select to_date('2008-01-01 14:26:38','YYYY-MM-DD HH24:MI:SS')
				// from dual

				value = "to_date('" + (String) values.get(i)
						+ "','YYYY-MM-DD HH24:MI:SS')";

				setSQL += value;

				if (pkColumnName.equalsIgnoreCase(fields.get(i)))

				{

					pkColumnValue = value;

				}

			}

			else

			{ // other types like long,double ,add nothing but their value

				value = (String) values.get(i);

				setSQL += value;

				if (pkColumnName.equalsIgnoreCase(fields.get(i)))

				{

					pkColumnValue = value;

				}

			}

			if (i != fields.size() - 1)

			{

				setSQL += ",";

			}

			updateSQL += setSQL;

		}

		updateSQL += " where " + pkColumnName + "=" + pkColumnValue;

		return updateSQL;

	}

	/**
	 * 
	 * @param Object
	 *            the object for insert
	 * 
	 * @return an insert String like below:
	 * 
	 *         insert into vmm_ag(sn,domainname,modifydate,testDouble)
	 * 
	 *         values(5,'domainname5',to_date('20080121','YYYYMMDD'),22.4)
	 */

	public String genDeleteSQL(Object bean)

	{

		List<String> fields = BeanUtil.getFields(bean);

		List<String> values = BeanUtil.getValues2String(bean);

		Hashtable fieldsTypes = BeanUtil.getFieldsAndTypes(bean);

		// get database table Name
 
		String pkType = (String) fieldsTypes.get(pkColumnName);

		String pkValue = "";

		for (int i = 0; i < fields.size(); i++)

		{ // find the pk column's value

			if (pkColumnName.toUpperCase().equalsIgnoreCase(fields.get(i)))

			{

				pkValue = values.get(i);

			}

		}

		// if the pkType is String ,then need to add '' around the value

		// number

		if ("String".equalsIgnoreCase(pkType))

		{

			pkValue = "'" + pkValue + "'";

		}

		// String deleteSQL ="delete "+tableName
		// +" where "+pkColumnName+"="+pkValue;

		String deleteSQL = genRemoveByIdSQL(tableName, pkValue);

		return deleteSQL;

	}

	/**
	 * 
	 * @param Object
	 *            the object for select
	 * 
	 * @return an select String like below:
	 * 
	 *         select * from vmm_ag
	 */

	public static String genFindAllSQL(String tableName)

	{

		String findAllSQL = "select  * from  " + tableName;

		return findAllSQL;

	}

	/**
	 * 
	 * @param Object
	 *            the object for select
	 * 
	 * @return an select String like below:
	 * 
	 *         select * from vmm_ag where sn=1
	 */

	public String genFindByIdSQL(String tableName, Serializable id)

	{

		String findByIdSQL = "select  * from  " + tableName + " where "
				+ pkColumnName + "=" + id;

		return findByIdSQL;

	}

	/**
	 * 
	 * @param Object
	 *            the object for select
	 * 
	 * @return an select String like below:
	 * 
	 *         select * from vmm_ag where sn=1
	 */

	public String genRemoveByIdSQL(String tableName, Serializable id)

	{

		String findByIdSQL = "delete from  " + tableName + " where "
				+ pkColumnName + "=" + id;

		return findByIdSQL;

	}

	 

}