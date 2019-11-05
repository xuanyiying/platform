package com.platform.common.util;

/**
 * @author wangying
 */
public class ArrayUtil {
    public static boolean notEmpty(byte[] bytes) {
        return !isEmpty(bytes);
    }

    public static boolean isEmpty(byte[] bytes) {
        return null == bytes || bytes.length == 0;
    }

    public static <T> boolean notEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

}
