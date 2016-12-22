package com.zyt.web.publics.base.mybatis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Hadoop
 * 
 */
public class SQLKit {
	/**
	 * 获取查询SQL的总数
	 * 
	 * @param originalSql
	 * @return
	 */
	public static String getTotalResultSQL(String originalSql) {

		originalSql = getLineSql(originalSql);
		int orderIndex = getLastOrderByPoint(originalSql);

		int formIndex = getAfterFORMPoint(originalSql);
		String select = originalSql.substring(0, formIndex);

		// 如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
		if (select.toUpperCase().indexOf("SELECT DISTINCT") != -1
				|| originalSql.toUpperCase().indexOf("GROUP BY") != -1) {
			return new StringBuffer(originalSql.length())
					.append("SELECT COUNT(1) COUNT FROM (")
					.append(originalSql.substring(0, orderIndex))
					.append(" ) T").toString();
		} else {
			return new StringBuffer(originalSql.length())
					.append("SELECT COUNT(1) COUNT ")
					.append(originalSql.substring(formIndex, orderIndex))
					.toString();
		}
	}

	/**
	 * 获取最后一个排序[ORDER BY] Index
	 * 
	 * @param originalSql
	 * @return
	 */
	private static int getLastOrderByPoint(String originalSql) {
		int orderIndex = originalSql.toUpperCase().lastIndexOf("ORDER BY");
		if (orderIndex == -1
				|| !isBracketCanPartnership(originalSql.substring(
						orderIndex, originalSql.length()))) {
			throw new RuntimeException("MySQL 分页必须要有Order by 语句!");
		}
		return orderIndex;
	}

	private static String getLineSql(String sql) {
		return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
	}

	private static int getAfterFORMPoint(String originalSql) {
		String regex = "\\s+FROM\\s+";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(originalSql);
		while (matcher.find()) {
			int fromStartIndex = matcher.start(0);
			String text = originalSql.substring(0, fromStartIndex);
			if (isBracketCanPartnership(text)) {
				return fromStartIndex;
			}
		}
		return 0;
	}

	/**
	 * 主要是产看括号是否成对出现
	 * 
	 * @param text
	 * @return
	 */
	private static boolean isBracketCanPartnership(String text) {
		if (text == null
				|| (getOccurrenceNum(text, '(') != getOccurrenceNum(text, ')'))) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param text
	 *            元字符串
	 * @param pery
	 *            查找对象
	 * @return
	 */
	private static int getOccurrenceNum(String text, char pery) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			count = (text.charAt(i) == pery) ? count + 1 : count;
		}
		return count;
	}
}
