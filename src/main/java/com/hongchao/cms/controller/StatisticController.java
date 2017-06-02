package com.hongchao.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by godlikehzj on 2017/5/30.
 */
@Controller
@RequestMapping(value = "statistic")
public class StatisticController extends BaseController{
    @RequestMapping(value = "list.do")
    public String getList(HttpServletRequest request, ModelMap modelMap){

        return "list.jsp";
    }
}
