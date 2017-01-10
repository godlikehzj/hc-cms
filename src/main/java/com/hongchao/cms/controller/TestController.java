package com.hongchao.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by godlikehzj on 2017/1/9.
 */
@Controller
public class TestController {
    @RequestMapping("mytest.do")
    public void mytest(){
        System.out.print("mytest");
    }
}
