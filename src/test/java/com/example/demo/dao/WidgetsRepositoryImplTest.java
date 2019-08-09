package com.example.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WidgetsRepositoryImplTest {



    @Autowired
    private Repository<Widget> dao;

    @Test
    @Order(1)
    public void contexLoads() throws Exception {
        assertThat(dao).isNotNull();
    }

    @Test
    @Order(2)
    public void create() throws Exception {
        Widget w = new Widget();
        w = dao.create(w);
        assertNotNull(w.getId());
    }

    @Test
    @Order(3)
    public void read() throws Exception {
        Widget w1 = new Widget();
        w1 = dao.create(w1);
        Widget w2 = dao.read(w1);
        assertEquals(w1.getId(), w2.getId());
    }

    @Test
    @Order(4)
    public void readAll() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Widget w = new Widget();
            dao.create(w);
        }
        assertEquals(1001, dao.readAll().size());
    }

    @Test
    @Order(5)
    public void update() throws Exception {
        Collection<Widget> collection = dao.readAll();
        for (Widget w : collection){
            w.setX(1000);
            w.setModifyTime(null);
            w = dao.update(w);
            assertNotNull(w.getModifyTime());
        }

        collection = dao.readAll();
        for (Widget w : collection){
            dao.read(w);
            assertEquals(1000, w.getX().longValue());
        }

        Exception e = null;
        Widget w = new Widget();
        try{
            dao.update(w);
        }catch (Exception ex){
            e = ex;
        }
        assertNotNull(e);


        w.setId(-1000L);
        try{
            dao.update(w);
        }catch (Exception ex){
            e = ex;
        }
        assertNotNull(e);
    }

    @Test
    @Order(6)
    public void delete() throws Exception {
        Collection<Widget> collection = dao.readAll();
        Collection<Widget> widgetsToDelete = new LinkedList<>(collection);
        for (Widget w : widgetsToDelete){
            dao.delete(w);
        }
        collection = dao.readAll();
        assertEquals(0, collection.size());
    }


}
