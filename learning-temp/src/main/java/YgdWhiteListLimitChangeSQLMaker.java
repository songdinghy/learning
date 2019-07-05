/*
 * Copyright (c) 2015-2025 by HRT All rights reserved.
 * @author wengyongyi
 */

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 
 * @author wengyongyi
 * @since 2018年7月25日
 * @version 1.0.0
 */
public class YgdWhiteListLimitChangeSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\润信小贷\\员工贷白名单\\万家冻结名单20190315.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}


		String sqlTemplate = "insert into `tmpaudit2018_limit_amt_log` (\n" +
				"`OPERATION_BATCH`,\n" +
				"`APPL_SEQ`,`LOAN_TYP`,`CUST_ID`,`CUST_NAME`,`LMT_NO`,`OLD_LMT_AMT`,\n" +
				"`NEW_LMT_AMT`,\n" +
				"`AUDIT_REMARK`\n" +
				") select \n" +
				" '20190415A' as OPERATION_BATCH,\n" +
				"`APPL_SEQ`,`LOAN_TYP`,`CUST_ID`,`CUST_NAME`,`LMT_NO`,`LMT_AMT` as `OLD_LMT_AMT`,\n" +
				"'%s' as NEW_LMT_AMT,\n" +
				"'根据风控部门要求将 %s员工贷额度调为%s' as AUDIT_REMARK\n" +
				"from lm_limit \n" +
				"where CUST_ID='%s' and LOAN_TYP='001';";


		String sqlTemplate2 = "update lm_limit set LMT_AMT='%s' where CUST_ID='%s' and LOAN_TYP='001';";

		int count = 0;
		List<String> sqlList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split("\t");

			String emplId = cols[0].trim();
			String emplName = cols[1].trim();
			String lmt_amt = cols[2].trim();

			String msg = String.format("emplId: %s, emplName: %s,lmt_amt: %s", emplId, emplName,lmt_amt);

			System.out.println(msg);

			String sql = String.format(sqlTemplate, lmt_amt,emplName,lmt_amt, emplId);

			String sql2 = String.format(sqlTemplate2 , lmt_amt,emplId);

			sqlList.add(sql2);
			sqlList.add(sql);

			count++;
		}

		System.out.println("Total: " + count);

		for (String sql : sqlList) {
			System.out.println(sql);
			System.out.println();
		}

	}

}
