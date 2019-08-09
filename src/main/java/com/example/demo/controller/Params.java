package com.example.demo.controller;

import com.example.demo.service.Filter;
import com.example.demo.service.Page;

public class Params<O> {

    private Page<O> page;
    private Filter filter;

    public Page<O> getPage() {
        return page;
    }

    public void setPage(Page<O> page) {
        this.page = page;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
