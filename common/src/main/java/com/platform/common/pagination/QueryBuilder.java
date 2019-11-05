package com.platform.common.pagination;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.common.util.ArrayUtil;
import com.platform.common.util.ColumnPropertyUtil;
import com.platform.common.util.StringUtil;
import com.platform.common.util.SystemDateTimeFormatter;

/**
 * @author wangying Created on 2019/9/26.
 */
public final class QueryBuilder {
	private static Logger log = LoggerFactory.getLogger(QueryBuilder.class);

	private QueryBuilder() {
		throw new IllegalStateException("Static utility class");
	}

	public static <T> QueryWrapper<T> build(PageParameter<T> of, String date, boolean likeQuery,
			String... ignoreLikeProperties) {
		QueryWrapper<T> queryWrapper = build(of.getSearch(), date, likeQuery, ignoreLikeProperties);
		queryWrapper.orderBy(true, of.isAsc(), of.orderBy());
		return queryWrapper;
	}

	public static <T> QueryWrapper<T> build(T search, String date, boolean likeQuery, String... ignoreLikeProperties) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		if (Objects.isNull(search)) {
			return queryWrapper;
		}
		if (likeQuery) {
			Field[] properties = search.getClass().getDeclaredFields();
			for (Field property : properties) {
				property.setAccessible(true);
				String name = property.getName();
				if (ArrayUtil.notEmpty(ignoreLikeProperties)) {
					for (String item : ignoreLikeProperties) {
						if (!item.equalsIgnoreCase(name)) {
							processPropertyType(search, date, queryWrapper, property, name);
						}
					}
				} else {
					processPropertyType(search, date, queryWrapper, property, name);
				}
			}
		} else {
			queryWrapper.setEntity(search);
		}
		return queryWrapper;
	}

	private static <T> void processPropertyType(T search, String date, QueryWrapper<T> queryWrapper, Field property,
			String name) {
		if (!Modifier.isStatic(property.getModifiers())) {
			if (property.getType() == String.class) {
				setString(queryWrapper, property, search, name);
			} else if (property.getType() == LocalDateTime.class) {
				setLocalDateTime(queryWrapper, name, date);
			} else if (property.getType() == LocalDate.class) {
				setLocalDate(queryWrapper, name, date);
			}
		}
	}

	private static <T> void setString(QueryWrapper<T> queryWrapper, Field property, T search, String name) {
		try {
			String value = (String) property.get(search);
			if (StringUtil.isNotEmpty(value)) {
				queryWrapper.like(ColumnPropertyUtil.column(name), value);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.warn("");
		}
	}

	private static <T> void setLocalDateTime(QueryWrapper<T> queryWrapper, String name, String date) {
		try {
			if (StringUtil.isNotEmpty(date)) {
				String[] dates = date.replace("[", "").replace("]", "").split(",");
				DateTimeFormatter formatter = SystemDateTimeFormatter.DATE_TIME_FORMATTER;
				queryWrapper.between(ColumnPropertyUtil.column(name),
						LocalDateTime.parse(dates[0].trim().replace("\"", "") + " 00:00:00", formatter),
						LocalDateTime.parse(dates[1].trim().replace("\"", "") + " 00:00:00", formatter));
			}
		} catch (IllegalArgumentException e) {
			log.warn("");
		}
	}

	private static <T> void setLocalDate(QueryWrapper<T> queryWrapper, String name, String date) {
		try {
			if (StringUtil.isNotEmpty(date)) {
				String[] dates = date.replace("[", "").replace("]", "").split(",");
				DateTimeFormatter formatter = SystemDateTimeFormatter.DATE_FORMATTER;
				queryWrapper.between(ColumnPropertyUtil.column(name),
						LocalDate.parse(dates[0].trim().replace("\"", ""), formatter),
						LocalDate.parse(dates[1].trim().replace("\"", ""), formatter));
			}
		} catch (IllegalArgumentException e) {
			log.warn("");
		}
	}
}
