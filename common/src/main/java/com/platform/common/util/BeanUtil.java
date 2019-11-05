package com.platform.common.util;

import java.util.Objects;
import org.springframework.beans.BeanUtils;

/**
 * @author wangying
 */
public final class BeanUtil {
    public static void copy(Object source, Object target) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanUtils.copyProperties(source, target);
        }
    }

    public static void copy(Object source, Object target, String... ignoreProperties) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanUtils.copyProperties(source, target, ignoreProperties);
        }
    }
}
