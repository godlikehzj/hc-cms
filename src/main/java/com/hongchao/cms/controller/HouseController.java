package com.hongchao.cms.controller;

import com.hongchao.cms.bean.HouseInfo;
import com.hongchao.cms.service.HouseService;
import com.hongchao.cms.service.mapper.HouseMapper;
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
public class HouseController extends BaseController{
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "list.do")
    public String getHouseList(ModelMap modelMap,int status) {
        List<HouseInfo> lists = houseService.getHouseList(status);
        modelMap.addAttribute("status", status);
        modelMap.addAttribute("houseLists", lists);

        return "list.jsp";
    }

    @RequestMapping(value = "toAdd.do")
    public String toAddHouse(){
        return "add.jsp";
    }

    @RequestMapping(value = "toEdit.do")
    public String toEditHouse(ModelMap modelMap, long houseId){
        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.addAttribute("houseInfo", houseInfo);

        return "edit.jsp";
    }

    @RequestMapping(value = "add.do")
    public void addHouse(HttpServletRequest request,
                         HttpServletResponse response,
                         String hname,
                         String addr,
                         String location){
        System.out.print(hname + addr + location);

        outResult(request, response,"json", houseService.addHouse(hname, addr, location));
    }

    @RequestMapping(value = "edit.do")
    public void editHouse(HttpServletRequest request,
                          HttpServletResponse response,
                          String hname,
                          String addr,
                          String location,
                          long id){
        outResult(request, response,"json", houseService.editHouse(id, hname, addr, location));
    }
    @RequestMapping(value = "changeStatu.do")
    public void changeStatu(HttpServletRequest request,
                            HttpServletResponse response,
                            Long houseId,
                            int statu){
        outResult(request, response, "json", houseService.changeStatus(houseId, statu));
    }
}
