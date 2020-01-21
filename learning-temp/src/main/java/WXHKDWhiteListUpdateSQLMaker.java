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
public class WXHKDWhiteListUpdateSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\万象花\\万象花\\新增万象花可借白名单0903.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}

		String sqlCustInfoExtTmpl = "INSERT into cust_specil (  `SPECIL_SEQ`, `INSTU_CDE`, `CUST_NAME`, `ID_TYP`, `ID_NO`, `SPECIL_TYP`, `SPECIL_STAGE`, `SPECIL_SOURCE`, `SPECIL_IND`, `SPECIL_R_DT`, `SPECIL_R_OPION`, `SPECIL_R_RSN`, `CRT_USR`, `CRT_DT`, `LAST_CHG_DT`, `LAST_CHG_USR`, `CRT_BCH`, `CUST_MOBILE`, `AUTH`, `depart_sta`, `SPECIL_CLS`)\n" +
				"VALUES('%s', '900000000', '%s', '20', '%s', '01', '04', '02', 'J', DATE_FORMAT(NOW(), '%s'), '03', '批量导入万象花可借白名单客户', 'admin', DATE_FORMAT(NOW(), '%s'), DATE_FORMAT(NOW(), '%s'), 'admin', '900000000', '%s', 'Y', 'N', '03');\n";

		SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String formatStr = "%Y-%m-%d %H:%i:%s";
		String formatStr1 = "%Y-%m-%d";
		Long seq = 5541932L;

		int count = 0;
		List<String> sqlCustInfoExtList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split(",");

			String specilSeq = String.valueOf(seq);

			String chnName = cols[0].trim();
			String idNo = cols[1].trim();
			String mobile = cols[2].trim();

			//时间格式转换
//			jionDate = newDateFormat.format(oldDateFormat.parse(jionDate));


			String msg = String.format(
					"specilSeq: %s, chnName: %s, idNo: %s, formatStr1: %s, formatStr1: %s, formatStr: %s, mobile: %s",
					specilSeq, chnName, idNo, formatStr1, formatStr1, formatStr, mobile);
			System.out.println(msg);

			String sql = String.format(sqlCustInfoExtTmpl, specilSeq, chnName, idNo, formatStr1, formatStr1, formatStr, mobile);
			sqlCustInfoExtList.add(sql);

			count++;
			seq = seq + 2;

		}

		System.out.println("Total: " + count);

		System.out.println("--万象花可借白名单");
		for (String sql : sqlCustInfoExtList) {
			System.out.println(sql);
			// System.out.println();
		}

	}

}
