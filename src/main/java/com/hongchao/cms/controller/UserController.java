package com.hongchao.cms.controller;

import com.hongchao.cms.bean.*;
import com.hongchao.cms.service.HouseService;
import com.hongchao.cms.service.UserService;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by godlikehzj on 2017/1/21.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "list.do")
    public String getList(HttpServletRequest request, ModelMap modelMap,int role, int status){
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        List<User> users = userService.getUsers(role, status, province, city, district);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
        modelMap.addAttribute("status", status);
        modelMap.addAttribute("users", users);

        return "list.jsp";
    }

    @RequestMapping(value = "toAdd.do")
    public String toAddUser(HttpServletRequest request, ModelMap modelMap, int role){
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
        List<HouseInfo> list;
        if (role != 1){
             list = houseService.getHouseList(1, province, city, district);

        }else{
            list = houseService.getHouseListByUid(1, province, city, district, 0);
        }
        modelMap.addAttribute("houseList", list);
        return "add.jsp";
    }

    @RequestMapping(value = "toEdit.do")
    public String toEditUser(HttpServletRequest request, ModelMap modelMap, long userId, int role){
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        User user = userService.getUserById(userId);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
//        List<HouseInfo> list = houseService.getHouseList(1, province, city, district);
        List<HouseInfo> list;
        if (role != 1){
            list = houseService.getHouseList(1, province, city, district);

        }else{
            list = houseService.getHouseListByUid(1, province, city, district, userId);
        }
        String[] ids = user.getHouseIds().split(",");
        for(HouseInfo houseInfo:list){
            for(String id : ids){
                if (id.equals(String.valueOf(houseInfo.getId()))){
                    houseInfo.setOwner(1);
                }
            }
        }
        modelMap.addAttribute("houseList", list);
        modelMap.addAttribute("user", user);

        return "edit.jsp";
    }

    @RequestMapping(value = "add.do")
    public void addUser(HttpServletRequest request,
                        HttpServletResponse response,
                        String mobile,
                        String name,
                        String houseIds,
                        int role){
        Integer province = Integer.valueOf(request.getHeader("province"));
        Integer city = Integer.valueOf(request.getHeader("city"));
        Integer district = Integer.valueOf(request.getHeader("district"));

        outResult(request, response, "json", userService.addUser(role, mobile, name, houseIds, province, city, district));
    }

    @RequestMapping(value = "edit.do")
    public void editUser(HttpServletRequest request,
                        HttpServletResponse response,
                        String mobile,
                        String name,
                        String houseIds,
                        long userId,
                         int role){
        outResult(request, response, "json", userService.editUser(userId, mobile, name, houseIds, role));
    }

    @RequestMapping(value = "changeStatu.do")
    public void changeStatu(HttpServletRequest request,
                            HttpServletResponse response,
                            Long userId,
                            int status){
        outResult(request, response, "json", userService.changeStatus(userId, status));
    }

    @RequestMapping(value = "cleanHis.do")
    public String getCleanHistory(ModelMap modelMap, Long userId,int role, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);

        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        User user = userService.getUserById(userId);

        List<AchieveHistory> achieveHistories = userService.getAchieveHistoryByUser(userId, from, to);
        modelMap.addAttribute("cleanHistoryList", achieveHistories);

        List<HandleHistory> handleHistories = houseService.getHandleStatusByUser(userId);
        modelMap.addAttribute("handleHistory", handleHistories);

        modelMap.addAttribute("role", role);
        modelMap.addAttribute("user", user);

        return "cleanhis.jsp";
    }

    @RequestMapping(value = "allClean.do")
    public String getAllCleanHistory(ModelMap modelMap){
        List<HandleHistory> handleHistories = houseService.getAllHandleHistory(2);
        Date now = new Date();
        for(HandleHistory handleHistory : handleHistories){
            if (handleHistory.getHandle_statu() == 1 && (now.getTime() - handleHistory.getHandleTime().getTime() > 15 * 60*1000)){
                handleHistory.setHandle_statu(2);
                houseService.updateHandleStatu(handleHistory.getId(), 2);
            }
        }
        modelMap.addAttribute("allCleanHistory", handleHistories);

        modelMap.addAttribute("role", 2);

        return "allClean.jsp";
    }

    @RequestMapping(value = "sortHis.do")
    public String getSortHistory(ModelMap modelMap, Long userId,int role, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);

        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        User user = userService.getUserById(userId);

        List<AchieveHistory> achieveHistories = userService.getAchieveHistoryByUser(userId, from, to);
        modelMap.addAttribute("sortAchieveList", achieveHistories);

        List<WeightHistory> sortHistory = houseService.getSortHistoryByUser(userId, from, to);
        modelMap.put("sortHistory", sortHistory);

        modelMap.addAttribute("role", role);
        modelMap.addAttribute("user", user);

        return "sorthis.jsp";
    }

    @RequestMapping(value = "allSort.do")
    public String getAllSortHistory(ModelMap modelMap){
        List<WeightHistory> sortHistory = houseService.getAllSortHistory();
        modelMap.addAttribute("allSortHistory", sortHistory);

        modelMap.addAttribute("role", 1);
        return "allSort.jsp";
    }

    @RequestMapping(value = "orderHis.do")
    public String getOrderHistory(ModelMap modelMap, Long userId, int role, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);

        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        User user = userService.getUserById(userId);
        List<Order> orderList = userService.getOrderHistoryByUser(userId, from, to);
        modelMap.addAttribute("orderHistoryList", orderList);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("user", user);

        return "orderhis.jsp";
    }

    @RequestMapping(value = "allRecover.do")
    public String getRecoverHistory(ModelMap modelMap){
        List<HandleHistory> handleHistories = houseService.getAllHandleHistory(3);
        Date now = new Date();
        for(HandleHistory handleHistory : handleHistories){
            if (handleHistory.getHandle_statu() == 1 && (now.getTime() - handleHistory.getHandleTime().getTime() > 15 * 60*1000)){
                handleHistory.setHandle_statu(2);
                houseService.updateHandleStatu(handleHistory.getId(), 2);
            }
        }
        modelMap.addAttribute("allRecoverHistory", handleHistories);

        modelMap.addAttribute("role", 3);

        return "allRecover.jsp";
    }
}
