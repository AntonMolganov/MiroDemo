package com.example.demo.controller;

import com.example.demo.dao.Widget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WidgetsControllerTest {



    @Autowired
    private WidgetsController controller;

    @Test
    @Order(1)
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @Order(2)
    public void create() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Widget w = new Widget();
            w.setX((int) (Math.random()*1000));
            w.setY((int) (Math.random()*1000));
            w.setWidth((int) (Math.random()*1000));
            w.setHeight((int) (Math.random()*1000));

            controller.create(w);
        }

        assertEquals(1000, controller.readAll(null).getCount());
    }

    @Test
    @Order(3)
    public void read() throws Exception {
        Widget w1 = new Widget();
        w1.setX((int) (Math.random()*1000));
        w1.setY((int) (Math.random()*1000));
        w1.setWidth((int) (Math.random()*1000));
        w1.setHeight((int) (Math.random()*1000));

        w1 = controller.create(w1);

        Widget w2 = controller.read(w1);

        assertEquals(w1.getId(), w2.getId());
    }

    @Test
    @Order(4)
    public void readAll() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Widget w = new Widget();
            w.setX((int) (Math.random()*1000));
            w.setY((int) (Math.random()*1000));
            w.setWidth((int) (Math.random()*1000));
            w.setHeight((int) (Math.random()*1000));

            controller.create(w);
        }

        assertEquals(1001, controller.readAll(null).getCount());
    }

    @Test
    @Order(5)
    public void update() throws Exception {
        Collection<Widget> collection = controller.readAll(null).getItems();
        for (Widget w : collection){
            w.setX(1000);
            controller.update(w);
        }

        collection = controller.readAll(null).getItems();

        for (Widget w : collection){
            controller.read(w);
            assertEquals(1000L, w.getX().longValue());
        }
    }

    @Test
    @Order(6)
    public void delete() throws Exception {
        Collection<Widget> collection = controller.readAll(null).getItems();
        for (Widget w : collection){
            controller.delete(w);
        }

        collection = controller.readAll(null).getItems();

        assertEquals(0, collection.size());
    }


}
