package com.example.demo.service;

import java.util.List;

public interface Service<O> {

    O create(O o) throws Exception;

    O read(O o) throws Exception;

    Page<O> readAll(Page<O> page, Filter filter);

    O update(O o) throws Exception;

    O delete(O o) throws Exception;
}
