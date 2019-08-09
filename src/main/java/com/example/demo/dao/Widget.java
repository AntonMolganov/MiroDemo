package com.example.demo.dao;

import java.util.Date;

public class Widget
        implements Cloneable{

    private Long id;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private Integer z;
    private Date modifyTime;

    public Widget() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public Widget clone() throws CloneNotSupportedException {
        Widget clone = (Widget) super.clone();
        clone.setModifyTime((Date) this.modifyTime.clone());
        return clone;
    }

}
