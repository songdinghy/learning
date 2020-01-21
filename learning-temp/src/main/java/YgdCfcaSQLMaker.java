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
public class YgdCfcaSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\万象花\\历史合同重签\\员工贷历史数据修复.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}


		String sqlExtTmpl = "insert into imp_unsign_data(LOAN_NO, PRODUCT_ID, REMARK, LAST_CHG_DT, STATUS) values" +
				"( '%s',  '%s', '历史员工贷合同补签', '2018-09-20', '1');";


		int count = 0;
		List<String> sqlExtList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split(",");

			String loanNo = cols[0].trim();
			String productType = cols[1].trim();


			String msg = String.format("loanNo: %s, productType: %s", loanNo, productType);
			System.out.println(msg);

			String sql = String.format(sqlExtTmpl, loanNo, productType);
			sqlExtList.add(sql);

			count++;

		}

		System.out.println("Total: " + count);

		System.out.println("-- 更新cust_info_ext记录中的字段");
		for (String sql : sqlExtList) {
			System.out.println(sql);
			// System.out.println();
		}

	}

}
