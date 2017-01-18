package com.hongchao.cms.controller;

import com.hongchao.cms.bean.HouseInfo;
import com.hongchao.cms.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by godlikehzj on 2017/1/12.
 */
@Controller
@RequestMapping(value = "house")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "list.do")
    public String getHouseList(ModelMap modelMap,int status) {
        List<HouseInfo> lists = houseService.getHouseList(status);
        modelMap.addAttribute("houseLists", lists);

        return "list.jsp";
    }

    @RequestMapping(value = "toAdd.do")
    public String toAddHouse(){
        return "add.jsp";
    }

    @RequestMapping(value = "add.do")
    public String addHouse(HttpServletRequest request,
                         HttpServletResponse response,
                         String hname,
                         String addr,
                           String location){
        System.out.print(hname + addr + location);
        return "list.jsp";
    }
}
