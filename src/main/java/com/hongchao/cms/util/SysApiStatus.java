package com.hongchao.cms.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by godlikehzj on 2017/1/21.
 */
public class SysApiStatus {
    public static Map<Integer,String> message=new HashMap<Integer, String>();;

    public static final Integer OK = 0; //业务正常
    public static final Integer OK_200 = 200; //业务正常
    public static final Integer ERROR = 500; //ERROR
    public static final Integer INVALID_CLIENTID = 501;
    public static final Integer SYSTEMERROR = 50000; //系统错误

    public static Map<Integer, String> roles = new HashMap<>();
    static {
        message.put(OK, "ok");
        message.put(OK_200, "ok");
        message.put(ERROR, "ERROR");
        message.put(SYSTEMERROR, "系统错误");
        message.put(INVALID_CLIENTID, "无效的clientId");

        roles.put(1, "分拣员");
        roles.put(2, "清运员");
        roles.put(3, "回收员");
    }

    public static String getMessage(Integer key)
    {
        return message.get(key);
    }
}
