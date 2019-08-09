package com.example.demo.dao;

import java.util.Collection;

public interface Repository<O> {

    O create(O o);

    O read(O o) throws Exception;

    Collection<Widget> readAll();

    O update(O o) throws Exception;

    O delete(O o) throws Exception;
}
