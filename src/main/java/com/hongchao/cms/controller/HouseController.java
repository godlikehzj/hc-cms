package com.hongchao.cms.controller;

import com.alibaba.fastjson.JSON;
import com.hongchao.cms.bean.*;
import com.hongchao.cms.service.CustomerService;
import com.hongchao.cms.service.HouseService;
import com.hongchao.cms.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = "getlist.do")
    public void getHouseListDetail(HttpServletRequest request, HttpServletResponse response) {
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        List<HouseInfo> lists = houseService.getHouseList(1, province, city, district);
        outResult(request, response, "json", JSON.toJSON(lists));
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
                         Long hid,
                         String hname,
                         String addr,
                         String location){
        System.out.print(hname + addr + location);
        Integer province = Integer.valueOf(request.getHeader("province") == null?"0":request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city")== null?"0":request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district")== null?"0":request.getHeader("district"));

        outResult(request, response, "json", houseService.addHouse(hid, hname, addr, location, province, city, district));
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

//        String indoor = houseInfo.getIndoor();
//        String outdoor = houseInfo.getOutdoor();
//        String resInfo = houseInfo.getRes_info();
//        //资源柜信息
//        List<String[]> zyg = new ArrayList<>();
//        for(int i = 0; i < indoor.length(); i++){
//            zyg.add(new String[]{indoor.substring(i, i + 1), outdoor.substring(i, i + 1), resInfo.substring(i, i + 1)});
//        }
//        modelMap.put("zygs", zyg);

        List<CabinetInfo> cabinetInfos = houseService.getCabinetInfo(houseId);
        List<CabinetInfo> zyg = new ArrayList<>(9);
        for(int i = 0; i < 9; i ++){
            zyg.add(new CabinetInfo());
        }
        for(CabinetInfo cabinetInfo:cabinetInfos){
            if (cabinetInfo.getDoorId() <= 8){
                zyg.add(cabinetInfo.getDoorId(), cabinetInfo);
            }
        }

        modelMap.put("zygs", zyg);

        List<HandleHistory> handleHistories = houseService.getHandleStatus(houseId);
        if (handleHistories.size() > 0){
            modelMap.put("handleStatu", 1);
        }else{
            modelMap.put("handleStatu", 0);
        }

        List<WeightHistory> weightHistories = houseService.getWeightHistory(houseId);
        String weightString ="";
        for(WeightHistory weight :weightHistories){
            weightString += weight.getWeight() + ",";
        }
        modelMap.put("weightHistory", weightString.length() > 0?weightString.substring(0, weightString.length() - 1):weightString);

        List<Integer> ecustatu = new ArrayList<>();
        for(int i = 1; i<=3; i++){
            if (RedisUtil.get("heartbeat/"+houseId+i) !=null){
                ecustatu.add(i-1, 1);
            }else{
                ecustatu.add(i-1, 0);
            }
        }

        modelMap.put("ecuStatu", ecustatu);

//        List<AchieveHistory> achieveHistories = houseService.getAchieveHistory(houseId);
//        modelMap.put("achieveHistory", achieveHistories);

        return "details.jsp";
    }

    @RequestMapping(value = "fixHis.do")
    public String getFixHistory(ModelMap modelMap, Long houseId, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);
        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        List<FixHistory> fixHistoryList = houseService.getFixHistory(houseId, from, to);
        modelMap.put("fixHistoryList", fixHistoryList);
        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.put("houseInfo", houseInfo);
        modelMap.put("houseId", houseId);

        return "fixhis.jsp";
    }

    @RequestMapping(value = "achieveHis.do")
    public String getAchieveHistory(ModelMap modelMap, Long houseId, String from, String to){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);
        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);


        List<AchieveHistory> achieveHistories = houseService.getAchieveHistory(houseId, 2, from, to);
        modelMap.put("achieveHistoryList", achieveHistories);
        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.put("houseInfo", houseInfo);
        modelMap.put("houseId", houseId);

        return "achievehis.jsp";
    }

    @RequestMapping(value = "orderHis.do")
    public String getOrderHistory(ModelMap modelMap, Long houseId, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);
        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);


        List<Order> orderList = houseService.getOrderHistory(houseId, from,  to);
        modelMap.put("orderHistoryList", orderList);
        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.put("houseInfo", houseInfo);
        modelMap.put("houseId", houseId);

        return "orderhis.jsp";
    }

    @RequestMapping(value = "sortHis.do")
    public String getSortHistory(ModelMap modelMap, Long houseId, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);
        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        List<User> sortUser = houseService.getUsersByhouseId(houseId, 1);
        modelMap.put("sortUser", sortUser);

        List<WeightHistory> sortHistory = houseService.getSortHistory(houseId, from, to);
        modelMap.put("sortHistory", sortHistory);

        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.put("houseInfo", houseInfo);
        modelMap.put("houseId", houseId);

        return "sort.jsp";
    }

    @RequestMapping(value = "putHis.do")
    public String getPutHistory(ModelMap modelMap, Long houseId, String from, String to){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);
        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        Integer times = houseService.getPutTimes(houseId, from, to);
        if (times == null){
            times = 0;
        }
        modelMap.addAttribute("times", times);

        List<CustomerWeight> weights = houseService.getCustomerWeightList(houseId, from, to);

        long wet = 0;
        long dry = 0;
        long total = 0;
        for(CustomerWeight weight:weights){
            if (weight.getCategory() == 0){
                total += weight.getWeight();
            }else if (weight.getCategory() == 1){
                weight.setUrl("customer/getPic.do?rid="+weight.getRid() + "&type=" + (weight.getCategory() - 1));
                dry += weight.getWeight();
            }else if (weight.getCategory() == 2){
                weight.setUrl("customer/getPic.do?rid="+weight.getRid() + "&type=" + (weight.getCategory() - 1));
                wet += weight.getWeight();
            }

        }
        modelMap.addAttribute("weights", weights);

        modelMap.addAttribute("total", total);
        modelMap.addAttribute("wet", wet);
        modelMap.addAttribute("dry", dry);

        List<CustomerAction> customerActions = houseService.getCustomerAction(houseId, from, to);
        modelMap.put("actions", customerActions);

        HouseInfo houseInfo = houseService.getHouseById(houseId);
        modelMap.put("houseInfo", houseInfo);
        modelMap.put("houseId", houseId);

        return "put.jsp";
    }

}
