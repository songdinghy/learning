/*
 * Copyright (c) 2015-2025 by HRT All rights reserved.
 * @author wengyongyi
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 
 * @author wust
 * @since 2018年7月25日
 * @version 1.0.0
 */
public class YgdWhiteListImportSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\润信小贷\\员工贷\\华网新增白名单0116.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}

//		String sqlTemplate = "insert into imp_cus_data (DATA_DATE, CHN_NAME, MEMBER_ID, LMT_AMT, NATIONAL_ID_TYPE, CERT_ID, MOBILE_PHONE, CRC_EDUCATION, BUSINESS_UNIT, CITY, SUPV_LVL_ID) \r\n";
//		sqlTemplate += "select '%s' as dt, '%s' as nm, t2.member_id, '%s' as amt, 'CHN18' as idtyp, t1.CERT_ID, '%s' as mb, '%s' as edu, '%s' as bu, '%s' as city, '%s' as lvl \r\n";
//		sqlTemplate += "from (select '%s' as CERT_ID from dual) t1 \r\n";
//		sqlTemplate += "left join (select member_id,id_no from cust_specil where id_no='%s' and member_id is not null limit 1) t2 \r\n";
//		sqlTemplate += "on t1.CERT_ID=t2.id_no;";

//		String sqlTemplate = "INSERT INTO imp_cus_data (\n" +
//				"DATA_DATE, EMP_ID, CHN_NAME, MEMBER_ID, \n" +
//				"LMT_AMT, NATIONAL_ID_TYPE, CERT_ID, MOBILE_PHONE, \n" +
//				"CRC_EDUCATION, EMPL_CLASS, BUSINESS_UNIT, CITY, SUPV_LVL_ID, CRC_JOINCRC_DT, ADDRESS1, LEAVE_OFFICE_IND) \n" +
//				"select \n" +
//				"'%s', '%s', '%s', t2.member_id, \n" +
//				"'%s', 'CHN18', '%s', t1.mobile, \n" +
//				"'%s', '%s', '%s', '%s', '%s', '%s', '深圳市南山区海斯路8号百度国际大厦26楼', '%s' \n" +
//				"FROM (\n" +
//				"\tSELECT '%s' as mobile FROM DUAL\n" +
//				") t1 LEFT JOIN (\n" +
//				"\tSELECT MEMBER_ID, CUST_MOBILE FROM cust_specil \n" +
//				"\tWHERE CUST_MOBILE = '%s' AND MEMBER_ID IS NOT NULL LIMIT 1\n" +
//				") t2 ON t1.mobile = t2.CUST_MOBILE;\n";


//		String sqlTemplate = "INSERT INTO imp_cus_data (\n" +
//				"DATA_DATE, EMP_ID, CHN_NAME, MEMBER_ID, \n" +
//				"LMT_AMT, NATIONAL_ID_TYPE, CERT_ID, MOBILE_PHONE, \n" +
//				"EMPL_CLASS, BUSINESS_UNIT, CITY, SUPV_LVL_ID, CRC_JOINCRC_DT, LEAVE_OFFICE_IND) \n" +
//				"select \n" +
//				"'%s', '%s', '%s', t2.member_id, \n" +
//				"'%s', 'CHN18', '%s', t1.mobile, \n" +
//				"'%s', '%s', '%s', '%s', '%s', '%s' \n" +
//				"FROM (\n" +
//				"\tSELECT '%s' as mobile FROM DUAL\n" +
//				") t1 LEFT JOIN (\n" +
//				"\tSELECT MEMBER_ID, CUST_MOBILE FROM cust_specil \n" +
//				"\tWHERE CUST_MOBILE = '%s' AND MEMBER_ID IS NOT NULL LIMIT 1\n" +
//				") t2 ON t1.mobile = t2.CUST_MOBILE;\n";

		String sqlTemplate = "INSERT INTO imp_cus_data (\n" +
				"DATA_DATE, EMP_ID, CHN_NAME, MEMBER_ID, \n" +
				"LMT_AMT, NATIONAL_ID_TYPE, CERT_ID, MOBILE_PHONE, \n" +
				"EMPL_CLASS, BUSINESS_UNIT, CITY, SUPV_LVL_ID, CRC_JOINCRC_DT, ADDRESS1, LEAVE_OFFICE_IND) \n" +
				"select \n" +
				"'%s', '%s', '%s', t2.member_id, \n" +
				"'%s', 'CHN18', '%s', t1.mobile, \n" +
				"'%s', '%s', '%s', '%s', '%s', '%s', '%s' \n" +
				"FROM (\n" +
				"\tSELECT '%s' as mobile FROM DUAL\n" +
				") t1 LEFT JOIN (\n" +
				"\tSELECT MEMBER_ID, CUST_MOBILE FROM cust_specil \n" +
				"\tWHERE CUST_MOBILE = '%s' AND MEMBER_ID IS NOT NULL LIMIT 1\n" +
				") t2 ON t1.mobile = t2.CUST_MOBILE;\n";

		String todayDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");


		int count = 0;
		List<String> sqlList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split("\t");

			//emplid	姓名	学历	身份证	手机号	BU	员工分类	加入华润时间	在职离职标签	工作城市	职级	授信额度	LEVEL	工作地点

//			String emplId = cols[0].trim();
//			String chnName = cols[1].trim();
//			String crcEduDesc = cols[2].trim();
//			String idNo = cols[3].trim();
//			String mobile = cols[4].trim();
//			String bu = cols[5].trim();
//			String classify = cols[6].trim();
//			String jionDate = cols[7].trim();
//			String label = cols[8].trim();
//			String city = cols[9].trim();
//			String supvLvlId = cols[10].trim();
//			String lmtAmt = cols[11].trim();

//			String emplId = cols[0].trim();
//			String chnName = cols[1].trim();
//			String idNo = cols[2].trim();
//			String mobile = cols[3].trim();
//			String bu = cols[4].trim();
//			String classify = cols[5].trim();
//			String jionDate = cols[6].trim();
//			String label = cols[7].trim();
//			String city = cols[8].trim();
//			String supvLvlId = cols[9].trim();
//			String lmtAmt = cols[10].trim();

			String emplId = cols[0].trim();
			String chnName = cols[1].trim();
			String idNo = cols[2].trim();
			String mobile = cols[3].trim();
			String bu = cols[4].trim();
			String classify = cols[5].trim();
			String jionDate = cols[6].trim();
			String label = cols[7].trim();
			String city = cols[8].trim();
			String supvLvlId = cols[9].trim();
			String lmtAmt = cols[10].trim();
			String address = cols[11].trim();
			String leve = cols[12].trim();


			//时间格式转换
			jionDate = newDateFormat.format(oldDateFormat.parse(jionDate));

			supvLvlId = supvLvlId.toUpperCase();

//			String msg = String.format(
//					"emplId: %s, chnName: %s, crcEduDesc: %s, idNo: %s, mobile: %s, bu: %s, classify: %s, jionDate: %s, label: %s, city: %s, supvLvlId: %s, lmtAmt: %s",
//					emplId, chnName, crcEduDesc, idNo, mobile, bu, classify, jionDate, label, city, supvLvlId, lmtAmt);
//			System.out.println(msg);
//
//			String sql = String.format(sqlTemplate, todayDateStr, emplId, chnName, lmtAmt, idNo, crcEduDesc, classify, bu, city,
//					supvLvlId, jionDate, label, mobile, mobile);

//			String msg = String.format(
//					"emplId: %s, chnName: %s, idNo: %s, mobile: %s, bu: %s, classify: %s, jionDate: %s, label: %s, city: %s, supvLvlId: %s, lmtAmt: %s",
//					emplId, chnName, idNo, mobile, bu, classify, jionDate, label, city, supvLvlId, lmtAmt);
//			System.out.println(msg);
//
//			String sql = String.format(sqlTemplate, todayDateStr, emplId, chnName, lmtAmt, idNo, classify, bu, city,
//					supvLvlId, jionDate, label, mobile, mobile);

			String msg = String.format(
					"emplId: %s, chnName: %s, idNo: %s, mobile: %s, bu: %s, classify: %s, jionDate: %s, label: %s, city: %s, supvLvlId: %s, lmtAmt: %s, address: %s",
					emplId, chnName, idNo, mobile, bu, classify, jionDate, label, city, supvLvlId, lmtAmt, address);
			System.out.println(msg);

			String sql = String.format(sqlTemplate, todayDateStr, emplId, chnName, lmtAmt, idNo, classify, bu, city,
					supvLvlId, jionDate, address, label, mobile, mobile);

			sqlList.add(sql);

			count++;
		}

		System.out.println("Total: " + count);

		StringBuffer buffer = new StringBuffer();
		for (String sql : sqlList) {
			System.out.println(sql);
			System.out.println();
//			buffer.append(sql);
//			buffer.append("\n");
		}
//		Files.write(Paths.get("D:/output.txt"), buffer.toString().getBytes());
	}

}
