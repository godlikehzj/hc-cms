package com.hongchao.cms.bean;

import java.util.Date;

/**
 * Created by godlikehzj on 2017/1/26.
 */
public class FixHistory {
    private String mobile;
    private String name;
    private long hid;
    private String content;
    private Date createTime;
    private String other_content;
    private int status;

    public FixHistory() {
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

    public long getHid() {
        return hid;
    }

    public void setHid(long hid) {
        this.hid = hid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOther_content() {
        return other_content;
    }

    public void setOther_content(String other_content) {
        this.other_content = other_content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
