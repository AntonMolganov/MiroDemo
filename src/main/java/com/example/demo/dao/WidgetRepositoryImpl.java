package com.example.demo.dao;

import org.springframework.stereotype.Component;

import java.util.*;

/* repository is not thread-safe */

@Component
public class WidgetRepositoryImpl
        implements Repository<Widget> {

    private final Object lock = new Object();
    private volatile long nextId = 1L;
    private Map<Long, Widget> storage = new HashMap<>();

    @Override
    public Widget create(Widget w) {
        w.setId(this.getNextId());
        w.setModifyTime(new Date());
        storage.put(w.getId(), w);
        return w;
    }

    @Override
    public Widget read(Widget w) throws Exception {
        if (w.getId() == null) throw new Exception("id not specified");
        if (!storage.containsKey(w.getId())) throw new Exception("item not found");
        return storage.get(w.getId());
    }

    @Override
    public Collection<Widget> readAll() {
        return storage.values();
    }

    @Override
    public Widget update(Widget w) throws Exception {
        if (w.getId() == null) throw new Exception("id not specified");
        if (!storage.containsKey(w.getId())) throw new Exception("item not found");
        w.setModifyTime(new Date());
        storage.put(w.getId(), w);
        return w;
    }

    @Override
    public Widget delete(Widget w) throws Exception {
        if (w.getId() == null) throw new Exception("id not specified");
        if (!storage.containsKey(w.getId())) throw new Exception("item not found");
        storage.remove(w.getId());
        return w;
    }

    private long getNextId(){
        synchronized (lock) {
            return nextId++;
        }
    }
}
