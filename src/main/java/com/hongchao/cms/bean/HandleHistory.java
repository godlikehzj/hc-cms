package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/1/29.
 */
public class HandleHistory {
    private long id;
    private String mobile;
    private String name;
    private String hname;
    private int handle_statu;
    private Date handleTime;

    public HandleHistory() {
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

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public int getHandle_statu() {
        return handle_statu;
    }

    public void setHandle_statu(int handle_statu) {
        this.handle_statu = handle_statu;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}
