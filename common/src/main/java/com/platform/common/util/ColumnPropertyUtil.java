package com.platform.common.util;

/**
 * Property and column conversion
 *
 * @author wangying
 */
public final class ColumnPropertyUtil {
	private static final String UNDERLINE = "_";

	private ColumnPropertyUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String column(String property) {
		if (StringUtil.isEmpty(property)) {
			return property;
		}
		StringBuilder column = new StringBuilder();
		for (int i = 0; i < property.length(); i++) {
			if (Character.isUpperCase(property.charAt(i))) {
				column.append(UNDERLINE).append(Character.toLowerCase(property.charAt(i)));
			} else {
				column.append(property.charAt(i));
			}
		}
		return column.toString();
	}

	public static String property(String column) {
		if (StringUtil.isEmpty(column) || !column.contains(UNDERLINE)) {
			return column;
		}
		StringBuilder property = new StringBuilder();
		int index = -1;
		for (int i = 0; i < column.length(); i++) {
			if ('_' == column.charAt(i)) {
				index = i + 1;
			} else if (i == index) {
				property.append(Character.toUpperCase(column.charAt(i)));
			} else {
				property.append(column.charAt(i));
			}
		}
		return property.toString();
	}

}
