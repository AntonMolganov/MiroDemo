package com.example.demo.service;

import java.util.List;

public class Page<O> {

    public final static int DEFAULT_SIZE = 10;
    public final static int DEFAULT_NUM = 0;
    private final static int MAX_PAGE_SIZE = 500;

    private Integer pageNum;
    private Integer pageSize;
    private List<O> items;
    private int count;

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if  (pageSize > MAX_PAGE_SIZE) pageSize = MAX_PAGE_SIZE;
        this.pageSize = pageSize;
    }

    public List<O> getItems() {
        return items;
    }

    public void setItems(List<O> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
