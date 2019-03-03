package com.doctor.common;

import java.util.Collections;
import java.util.List;

/**
 * 分页器
 */
public class Pager<T> {
    private static final int DEFAULT_SIZE = 10;

    // 当前页
    private int page = 1;
    // 每页数量
    private int size = DEFAULT_SIZE;
    // 总行数
    private int count = 0;
    // 最大页
    private int max = 0;
    // 行列表
    private List<T> rows;

    public Pager() { }

    public Pager(int page, int size, int count) {
        this(page, size, count, Collections.emptyList());
    }

    public Pager(int page, int size, int count, List<T> rows) {
        this.page = page;
        this.size = size > 0 ? size : DEFAULT_SIZE;
        this.count = count;
        this.rows = rows;

        this.max = count > 0 ? ((0 == count % size) ? (count / size) : (1 + count / size)) : 0;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
