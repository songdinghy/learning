/*
 * Copyright (c) 2015-2025 by HRT All rights reserved.
 * @author wengyongyi
 */

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author wust
 * @since 2018年9月21日
 * @version 1.0.0
 */
public class YgdWhiteListUpdateNameSQLMaker {

	public static void main(String[] args) throws Exception {
		File file = new File("F:\\万象花\\员工贷\\更正员工白名单姓名0920.txt");

		System.out.println(file.getPath());
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		if (null == lines || lines.isEmpty() || lines.size() < 2) {
			return;
		}


		String sqlExtTmpl = "update cust_specil set CUST_NAME = '%s' where CUST_ID = '%s' and CUST_NAME = '%s';";


		int count = 0;
		List<String> sqlExtList = new ArrayList<>();
		for (int i = 1, size = lines.size(); i < size; i++) {
			String line = lines.get(i);
			if (null == line || 0 == line.trim().length()) {
				continue;
			}

			String[] cols = line.trim().split("\t");

			String custId = cols[0].trim();
			String oldCustName = cols[1].trim();
			String newCustName = cols[2].trim();


			String msg = String.format("custId: %s, oldCustName: %s, newCustName: %s", custId, oldCustName, newCustName);
			System.out.println(msg);

			String sql = String.format(sqlExtTmpl, newCustName, custId, oldCustName);
			sqlExtList.add(sql);

			count++;

		}

		System.out.println("Total: " + count);

		for (String sql : sqlExtList) {
			System.out.println(sql);
			// System.out.println();
		}

	}

}
