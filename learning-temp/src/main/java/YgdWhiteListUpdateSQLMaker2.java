/*
 * Copyright (c) 2015-2025 by HRT All rights reserved.
 * @author wengyongyi
 */

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
public class YgdWhiteListUpdateSQLMaker2 {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\润信小贷\\员工贷\\员工贷白名单添加活跃度测试【20181115】.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}

//		String sqlCustInfoExtTmpl = "update cust_info_ext set CRC_JOINCRC_DT='%s' "
//				+ "where CUST_ID=(select distinct CUST_ID from cust_specil where ID_NO='%s' and CUST_NAME='%s' and SPECIL_CLS='01');";

		String sqlCustInfoExtTmpl = "UPDATE cust_specil SET LAST_CHG_DT=DATE_FORMAT(NOW(), '%s'), \n" +
				"`LEVEL`='%s' WHERE CUST_MOBILE='%s' AND SPECIL_CLS='01';";

//		String sqlCustInfoExtTmpl = "UPDATE cust_specil SET LAST_CHG_DT=DATE_FORMAT(NOW(), 'yyyy-MM-dd HH:mm:ss'), \n" +
//				"`LEVEL`='%str' WHERE CUST_MOBILE='%str' AND SPECIL_CLS='01';";

		SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String formatStr = "%Y-%m-%d %H:%i:%s";

		int count = 0;
		List<String> sqlCustInfoExtList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split("\t");

			//emplid	姓名	学历	身份证	手机号	BU	员工分类	加入华润时间	在职离职标签	工作城市	职级	授信额度	LEVEL

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
//			String level = cols[12].trim();

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
			String level = cols[12].trim();

			//时间格式转换
//			jionDate = newDateFormat.format(oldDateFormat.parse(jionDate));

			supvLvlId = supvLvlId.toUpperCase();

			String msg = String.format(
					"formatStr: %s, mobile: %s, level: %s",
					formatStr, mobile, level);
			System.out.println(msg);

			String sql = String.format(sqlCustInfoExtTmpl, formatStr, level, mobile);
			sqlCustInfoExtList.add(sql);

			count++;

		}

		System.out.println("Total: " + count);

		StringBuffer buffer = new StringBuffer();
		for (String sql : sqlCustInfoExtList) {
			System.out.println(sql);
			System.out.println();
//			buffer.append(sql);
//			buffer.append("\n");
		}
//		Files.write(Paths.get("D:/output.txt"), buffer.toString().getBytes());

	}

}
