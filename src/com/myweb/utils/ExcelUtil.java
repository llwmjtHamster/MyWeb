package com.myweb.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	String kaoshiid = "";

	DB db = new DB();

	public void add(String excelPath) {
		try {
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(
					excelPath));
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
			int rows = sheet.getPhysicalNumberOfRows();
			for (int i = 0; i < rows; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					for (int j = 0; j < cells; j++) {
						HSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue() + "|";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + "|";
								break;
							default:
								value += "0";
								break;
							}
						}
					}
					if (i > 0) {
						String[] val = value.split("\\|");

						String kaoshi = val[0];

						String kemu = val[1];

						if (kaoshi != null && kaoshi.length() > 0) {
							if (kaoshiid.length() < 1) {
								String sql = "select * from t_kaoshi t where t.kaoshi='"
										+ kaoshi + "' and kemu='" + kemu + "'";

								Object[] params1 = {};

								try {
									db.doPstm(sql, params1);

									ResultSet rs = db.getRs();

									if (rs.next()) {
										kaoshiid = String.valueOf(rs
												.getInt("id"));
									}
								} catch (Exception ex) {

								}
							}
						}

						if (kaoshi != null && kaoshi.length() > 0) {

							String sql = "insert into  t_chengji (kaoshiid,xueshengid,chengji) SELECT "
									+ kaoshiid
									+ ",t1.id,'"
									+ val[3].trim()
									+ "' FROM t_xuesheng t1 where t1.loginname='"
									+ val[2].trim() + "'";
							
							Object[] params1 = {};
							
							db.doPstm(sql, params1);

							System.out.print(sql);

						}

					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}