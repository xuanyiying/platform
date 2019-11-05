package com.platform.common.util;

import java.nio.charset.StandardCharsets;

/**
 * @author wangying
 */
public class StringUtil {
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static byte[] getBytes(String value) {
        return isEmpty(value) ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
    }

}
