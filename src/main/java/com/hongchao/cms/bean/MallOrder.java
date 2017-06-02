package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/5/29.
 */
public class MallOrder {
    private String nick;
    private Integer num;
    private String commodityName;
    private String description;
    private Integer cost_point;
    private String img;
    private Date createTime;

    public MallOrder() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost_point() {
        return cost_point;
    }

    public void setCost_point(Integer cost_point) {
        this.cost_point = cost_point;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
