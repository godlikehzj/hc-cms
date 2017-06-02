package com.hongchao.cms.bean;

/**
 * Created by godlikehzj on 2017/5/31.
 */
public class MallCommodity {
    private long id;
    private String name;
    private String description;
    private int cost_point;
    private String img;
    private int statu;

    public MallCommodity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost_point() {
        return cost_point;
    }

    public void setCost_point(int cost_point) {
        this.cost_point = cost_point;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }
}
