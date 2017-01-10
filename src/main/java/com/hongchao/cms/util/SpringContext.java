package com.hongchao.cms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by godlikehzj on 2017/1/9.
 */
public class SpringContext implements ApplicationContextAware {

    protected static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String name){
        return getContext().getBean(name);
    }
}
