package com.platform.common.util;

import java.util.Collection;

/**
 * @author wangying
 */
public final class Collections {
    public static boolean nonNull(Collection <?> collection) {
        return !isNull(collection);
    }

    public static boolean isNull(Collection <?> collection) {
        return null == collection || collection.isEmpty();
    }
}
