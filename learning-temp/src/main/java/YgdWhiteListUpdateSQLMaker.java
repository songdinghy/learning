/*
 * Copyright (c) 2015-2025 by HRT All rights reserved.
 * @author wengyongyi
 */

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author wengyongyi
 * @since 2018年7月25日
 * @version 1.0.0
 */
public class YgdWhiteListUpdateSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("E:\\ProjectDocs\\润信小货\\零星任务\\20180730补全白名单字段\\华网新增白名单0730.csv.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}

		String address1 = "深圳市南山区海斯路8号百度国际大厦26楼";

		String sqlCustInfoExtTmpl = "update cust_info_ext set EMP_ID='%s', EMPL_CLASS='%s', CRC_ENTRY_DT='%s', CITY='%s', ADDRESS1='%s' "
				+ "where CUST_ID=(select distinct CUST_ID from cust_specil where ID_NO='%s' and CUST_NAME='%s' and SPECIL_CLS='01');";

		String sqlCustSpecilTmpl = "update cust_specil set depart_sta='Y' where ID_NO='%s' and CUST_NAME='%s' and SPECIL_CLS='01';";

		SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		int count = 0;
		List<String> sqlCustInfoExtList = new ArrayList<>();
		List<String> sqlCustSpecilList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split(",");

			String emplId = cols[0].trim();
			String chnName = cols[1].trim();
			String crcEduDesc = cols[2].trim();
			String idNo = cols[3].trim();
			String mobile = cols[4].trim();
			String bu = cols[5].trim();
			String emplClass = cols[6].trim();
			String joinCrcDate = cols[7].trim();
			String departSta = cols[8].trim();
			String city = cols[9].trim();
			String supvLvlId = cols[10].trim();
			String dutyCat = cols[11].trim();
			String lmtAmt = cols[12].trim();

			joinCrcDate = newDateFormat.format(oldDateFormat.parse(joinCrcDate));

			String msg = String.format(
					"emplId: %s, chnName: %s, crcEduDesc: %s, idNo: %s, mobile: %s, bu: %s, emplClass: %s, joinCrcDate: %s, departSta: %s, city: %s, supvLvlId: %s, lmtAmt: %s",
					emplId, chnName, crcEduDesc, idNo, mobile, bu, emplClass, joinCrcDate, departSta, city, supvLvlId,
					lmtAmt);
			System.out.println(msg);
			String sql = String.format(sqlCustInfoExtTmpl, emplId, emplClass, joinCrcDate, city, address1, idNo,
					chnName);
			sqlCustInfoExtList.add(sql);

			count++;

			if ("I".equals(departSta)) {
				String updCustSpecilSql = String.format(sqlCustSpecilTmpl, idNo, chnName);
				sqlCustSpecilList.add(updCustSpecilSql);
			}

		}

		System.out.println("Total: " + count);

		System.out.println("-- 更新cust_info_ext记录中的字段");
		for (String sql : sqlCustInfoExtList) {
			System.out.println(sql);
			// System.out.println();
		}

		if (sqlCustSpecilList.size() > 0) {
			System.out.println();
			System.out.println("-- 更新cust_specil记录中的字段");
			for (String sql : sqlCustSpecilList) {
				System.out.println(sql);
				// System.out.println();
			}
		}

	}

}
