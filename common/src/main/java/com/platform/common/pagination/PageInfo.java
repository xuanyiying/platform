package com.platform.common.pagination;

import java.util.List;

/**
 * @author wangying
 * Created on 2019/9/26.
 */
public class PageInfo<T> {
    /**
     * return PageInfo result
     */
    private long total;
    private List <T> list;
    private long pages;
    /**
     * query parameter
     */
    private int page;
    private int limit;

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public PageInfo(long total, List<T> list, long pages) {
        this.total = total;
        this.list = list;
        this.pages = pages;
    }

    public PageInfo(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public PageInfo(long total, List <T> list, int page, int limit, int pages) {
        this.total = total;
        this.list = list;
        this.page = page;
        this.limit = limit;
        this.pages = pages;
    }

    public static <T> PageInfo <T> of(long total, List <T> list, long pages) {
        return new PageInfo <>(total, list, pages);
    }

    public static <T> PageInfo <T> forQuery(int page, int limit) {
        return new PageInfo <>(page, limit);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List <T> getList() {
        return list;
    }

    public void setList(List <T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}

