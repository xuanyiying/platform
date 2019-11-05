package com.platform.common.util;

import java.time.format.DateTimeFormatter;

/**
 * @author wangying
 */
public final class SystemDateTimeFormatter {
	private SystemDateTimeFormatter() {
		throw new IllegalStateException("Utility class");
	}

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d");

}
