package com.platform.common.pagination;

import com.platform.common.util.ColumnPropertyUtil;

/**
 * @author wangying
 */
public class PageParameter<T> {
	private int limit;
	private int page;
	private T search;
	private String sort;
	private boolean isAsc;

	public int getLimit() {
		return limit;
	}

	public int getPage() {
		return page;
	}

	public T getSearch() {
		return search;
	}

	public String orderBy() {
		return ColumnPropertyUtil.column(sort);
	}

	private PageParameter(Builder<T> builder) {
		super();
		this.limit = builder.limit;
		this.page = builder.page;
		this.search = builder.search;
		this.sort = builder.sort;
		this.isAsc = setAsc(builder.direction);
	}

	private boolean setAsc(String direction) {
		return "ASC".equalsIgnoreCase(direction);
	}

	public boolean isAsc() {
		return isAsc;
	}

	public static final class Builder<T> {
		private int limit;
		private int page;
		private T search;
		private String sort;
		private String direction;

		public Builder<T> page(int page, int limit) {
			this.page = page;
			this.limit = limit;
			return this;
		}

		public Builder<T> sort(String sort, String direction) {
			this.direction = direction;
			this.sort = sort;
			return this;
		}

		private Builder() {
		}

		public Builder<T> search(T search) {
			this.search = search;
			return this;
		}

		public PageParameter<T> build() {
			return new PageParameter<>(this);
		}
	}

	public static <T> Builder<T> builder() {
		return new Builder<>();
	}

}
