package com.platform.common;

import java.lang.reflect.Field;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.common.exception.UniqueException;
import com.platform.common.util.ArrayUtil;
import com.platform.common.util.ColumnPropertyUtil;

/**
 * @author wangying Created on 2019/10/11.
 */
public final class CheckUnique {
	private static Logger log = LoggerFactory.getLogger(CheckUnique.class);

	private CheckUnique() {
	}

	public static <T> void checkUnique(T entity, BaseMapper<T> mapper, boolean update, String... properties)
			throws UniqueException {
		if (ArrayUtil.isEmpty(properties)) {
			return;
		}
		if (Objects.nonNull(entity)) {
			QueryWrapper<T> query = new QueryWrapper<>();
			String column;
			String value = "";
			Field property;
			Class<?> entityClass = entity.getClass();
			for (String item : properties) {
				column = ColumnPropertyUtil.column(item);
				try {
					property = entityClass.getDeclaredField(item);
					property.setAccessible(true);
					value = (String) property.get(entity);
					query.eq(column, value);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					log.warn("Check insert {} unique met exception, error info: {}", entity.getClass().getSimpleName(),
							e);
				}
			}
			T existedEntity = mapper.selectOne(query);
			if (Objects.nonNull(existedEntity)) {
				if (!update) {
					throw new UniqueException(value);
				} else {
					Integer existedId = getIdValue(existedEntity);
					Integer entityId = getIdValue(entity);
					if (Objects.nonNull(entityId) && !entityId.equals(existedId)) {
						throw new UniqueException(value);
					}
				}
			}
		}
	}

	private static <T> Integer getIdValue(T entity) {
		try {
			Field field = entity.getClass().getDeclaredField("id");
			field.setAccessible(true);
			return (Integer) field.get(entity);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			log.warn("Get field value met exception, error info: {}",e);
		}
		return null;
	}
}
