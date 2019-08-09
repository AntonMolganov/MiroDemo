package com.example.demo.controller;

import com.example.demo.dao.Widget;
import com.example.demo.service.Filter;
import com.example.demo.service.Page;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miro")
public class WidgetsController {

    @Autowired
    Service<Widget> service;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Widget create(@RequestBody Widget widget) throws Exception {
        return service.create(widget);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/read")
    public Widget read(@RequestBody Widget widget) throws Exception {
        return service.read(widget);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/readAll")
    public Page<Widget> readAll(@Nullable @RequestBody Params<Widget> params) {
        Page<Widget> page = null;
        Filter filter = null;
        if (params != null) {
           page = params.getPage();
           filter = params.getFilter();
        }

        if (page != null) {
            if (page.getPageNum() == null) page.setPageNum(Page.DEFAULT_NUM);
            if (page.getPageSize() == null) page.setPageSize(Page.DEFAULT_SIZE);
        }

        if (filter != null) {
            if (filter.getX() == null) filter.setX(Filter.DEFAULT_X);
            if (filter.getY() == null) filter.setY(Filter.DEFAULT_Y);
            if (filter.getWidth() == null) filter.setWidth(Filter.DEFAULT_WIDTH);
            if (filter.getHeight() == null) filter.setHeight(Filter.DEFAULT_HEIGHT);
        }

        return service.readAll(page, filter);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Widget update(@RequestBody Widget widget) throws Exception {
        return service.update(widget);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public Widget delete(@RequestBody Widget widget) throws Exception {
        return service.delete(widget);
    }
}
