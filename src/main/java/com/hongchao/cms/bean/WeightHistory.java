package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/2/11.
 */
public class WeightHistory {
    private String nick;
    private String img;
    private String user;
    private int category;
    private int weight;
    private Date createTime;
    private String photo;
    private String hname;
    private int wet;
    private int dry;

    public WeightHistory() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public int getWet() {
        return wet;
    }

    public void setWet(int wet) {
        this.wet = wet;
    }

    public int getDry() {
        return dry;
    }

    public void setDry(int dry) {
        this.dry = dry;
    }
}
