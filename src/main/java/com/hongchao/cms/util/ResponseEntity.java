package com.hongchao.cms.util;

/**
 * Created by godlikehzj on 2017/1/21.
 */
public class ResponseEntity {
    private static final long serialVersionUID = 1L;

    private String message;
    private Integer status;
    private Object data;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public ResponseEntity(Integer status, String message, Object data) {
        super();
        this.message = message;
        this.status = status;
        this.data = data;
    }
    public ResponseEntity() {
        super();
    }
}
