package com.example.demo.service;

import com.example.demo.dao.Repository;
import com.example.demo.dao.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@org.springframework.stereotype.Service
public class WidgetsServiceImpl
        implements Service<Widget> {

    @Autowired
    private Repository<Widget> dao;

    private int currentMaxZ = 0;

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private final static Comparator<Widget> idComparator = (widget1, widget2) -> {
        if (widget1 == null) return 1;
        if (widget2 == null) return -1;
        return widget1.getId().compareTo(widget2.getId());
    };

    @Override
    public Widget create(Widget newWidget) throws Exception {
        if (newWidget.getX() == null
                || newWidget.getY() == null
                || newWidget.getWidth() == null
                || newWidget.getHeight() == null)
            throw new Exception("Required params not specified");


        rwl.writeLock().lock();
        try {
            if (newWidget.getZ() == null) {
                currentMaxZ++;
                newWidget.setZ(currentMaxZ);
                return dao.create(newWidget);
            }

            Collection<Widget> widgets = dao.readAll();
            for (Widget w : widgets){
                if (w.getZ() >= newWidget.getZ()) w.setZ(w.getZ() + 1);
                if (w.getZ() > currentMaxZ) currentMaxZ = w.getZ();
            }

            if (newWidget.getZ() > currentMaxZ) currentMaxZ = newWidget.getZ();

            return dao.create(newWidget);
        }finally {
            rwl.writeLock().unlock();
        }
    }


    @Override
    public Widget read(Widget widget) throws Exception {
        rwl.readLock().lock();
        try {
            return dao.read(widget);
        }finally {
            rwl.readLock().unlock();
        }
    }

    @Override
    public Page<Widget> readAll(Page<Widget> page, Filter filter) {
        rwl.readLock().lock();
        try {
            List<Widget> widgets = new LinkedList<>(dao.readAll());

            List<Widget> filteredWidgets = new LinkedList<>();
            if (filter != null) {
                Rectangle filterRectangle = new Rectangle(filter.getX(), filter.getY(), filter.getWidth(), filter.getHeight());
                for (Widget w : widgets){
                    Rectangle2D intersection = filterRectangle.createIntersection(new Rectangle(w.getX(), w.getY(), w.getWidth(), w.getHeight()));
                    if (intersection.getWidth() > 0 || intersection.getHeight() > 0) filteredWidgets.add(w);
                }
            }else{
                filteredWidgets = widgets;
            }

            filteredWidgets.sort(idComparator);

            int fromIndex = 0;
            int toIndex = filteredWidgets.size();

            if (page == null) {
                page = new Page<>(0, filteredWidgets.size());
            } else {
                fromIndex = page.getPageSize() * page.getPageNum();
                if (fromIndex < 0) fromIndex = 0;
                toIndex = page.getPageSize() * (page.getPageNum() + 1);
                if (toIndex > filteredWidgets.size()) toIndex = filteredWidgets.size();
            }
            page.setItems(filteredWidgets.subList(fromIndex, toIndex));
            page.setCount(filteredWidgets.size());
            return page;
        }finally {
            rwl.readLock().unlock();
        }
    }

    @Override
    public Widget update(Widget widget) throws Exception {
        if (widget.getX() == null
                || widget.getY() == null
                || widget.getWidth() == null
                || widget.getHeight() == null)
            throw new Exception("Required params not specified");

        rwl.writeLock().lock();
        try{
            return dao.update(widget);
        }finally {
            rwl.writeLock().unlock();
        }
    }

    @Override
    public Widget delete(Widget widget) throws Exception {
        rwl.writeLock().lock();
        try{
            return dao.delete(widget);
        }finally {
            rwl.writeLock().unlock();
        }
    }
}
