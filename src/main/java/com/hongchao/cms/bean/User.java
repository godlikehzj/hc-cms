package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/1/21.
 */
public class User {
    private long id;
    private String mobile;
    private String name;
    private Date create_time;
    private Date modify_time;
    private String houseIds;
    private String lastHouseName;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getHouseIds() {
        return houseIds;
    }

    public void setHouseIds(String houseIds) {
        this.houseIds = houseIds;
    }

    public String getLastHouseName() {
        return lastHouseName;
    }

    public void setLastHouseName(String lastHouseName) {
        this.lastHouseName = lastHouseName;
    }
}
