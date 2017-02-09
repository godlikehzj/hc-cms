package com.hongchao.cms.controller;

import com.hongchao.cms.bean.*;
import com.hongchao.cms.service.HouseService;
import com.hongchao.cms.service.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    public String getHouseList(HttpServletRequest request, ModelMap modelMap,int status) {
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        List<HouseInfo> lists = houseService.getHouseList(status, province, city, district);
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
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        outResult(request, response, "json", houseService.addHouse(hname, addr, location, province, city, district));
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

    @RequestMapping(value = "details.do")
    public String getDetails(ModelMap modelMap, Long houseId){

        modelMap.put("houseId", houseId);

        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.addAttribute("houseInfo", houseInfo);
        String[] capacitys = houseInfo.getCapacity().split(",");
        modelMap.addAttribute("capacitys", capacitys);

        String indoor = houseInfo.getIndoor();
        String outdoor = houseInfo.getOutdoor();
        String resInfo = houseInfo.getRes_info();
        //资源柜信息
        List<String[]> zyg = new ArrayList<>();
        for(int i = 0; i < indoor.length(); i++){
            zyg.add(new String[]{indoor.substring(i, i + 1), outdoor.substring(i, i + 1), resInfo.substring(i, i + 1)});
        }
        modelMap.put("zygs", zyg);


        List<User> fenjian = houseService.getUsersByhouseId(houseId, 1);
        modelMap.put("fenjian", fenjian);

        List<AchieveHistory> achieveHistories = houseService.getAchieveHistory(houseId);
        modelMap.put("achieveHistory", achieveHistories);

        return "details.jsp";
    }

    @RequestMapping(value = "fixHis.do")
    public String getFixHistory(ModelMap modelMap, Long houseId){
        List<FixHistory> fixHistoryList = houseService.getFixHistory(houseId);
        modelMap.put("fixHistoryList", fixHistoryList);
        modelMap.put("houseId", houseId);

        return "fixhis.jsp";
    }

    @RequestMapping(value = "achieveHis.do")
    public String getAchieveHistory(ModelMap modelMap, Long houseId){

        List<AchieveHistory> achieveHistories = houseService.getAchieveHistory(houseId);
        modelMap.put("achieveHistoryList", achieveHistories);
        modelMap.put("houseId", houseId);

        return "achievehis.jsp";
    }

    @RequestMapping(value = "orderHis.do")
    public String getOrderHistory(ModelMap modelMap, Long houseId){
        List<Order> orderList = houseService.getOrderHistory(houseId);
        modelMap.put("orderHistoryList", orderList);
        modelMap.put("houseId", houseId);

        return "orderhis.jsp";
    }
}
