package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/3/30.
 */
public class CustomerAction {
    private long id;
    private String nick;
    private long houseId;
    private int doorId;
    private Date createTime;

    public CustomerAction() {
    }

    public CustomerAction(long id, String nick, long houseId, int doorId, Date createTime) {
        this.id = id;
        this.nick = nick;
        this.houseId = houseId;
        this.doorId = doorId;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
