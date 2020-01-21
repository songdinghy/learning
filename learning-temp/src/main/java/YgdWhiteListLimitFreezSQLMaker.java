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
 * @author wust
 * @since 2018年9月15日
 * @version 1.0.0
 */
public class YgdWhiteListLimitFreezSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\万象花\\员工贷\\冻结额度用户0925.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}

		String sqlTemplate = "update lm_limit set lmt_sts = '05', freez_op_ind = 'H' where cust_id = '%s';";



		int count = 0;
		List<String> sqlList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split("\t");


			String emplId = cols[0].trim();


			String msg = String.format("emplId: %s", emplId);

			System.out.println(msg);

			String sql = String.format(sqlTemplate, emplId);

			sqlList.add(sql);

			count++;
		}

		System.out.println("Total: " + count);

		System.out.println("-- 员工贷额度冻结");

		for (String sql : sqlList) {
			System.out.println(sql);
			System.out.println();
		}

	}

}
