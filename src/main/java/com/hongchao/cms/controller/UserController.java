package com.hongchao.cms.controller;

import com.hongchao.cms.bean.AchieveHistory;
import com.hongchao.cms.bean.HouseInfo;
import com.hongchao.cms.bean.Order;
import com.hongchao.cms.bean.User;
import com.hongchao.cms.service.HouseService;
import com.hongchao.cms.service.UserService;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        List<HouseInfo> list = houseService.getHouseList(1, province, city, district);
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
        List<HouseInfo> list = houseService.getHouseList(1, province, city, district);
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
                        long userId){
        outResult(request, response, "json", userService.editUser(userId, mobile, name, houseIds));
    }

    @RequestMapping(value = "changeStatu.do")
    public void changeStatu(HttpServletRequest request,
                            HttpServletResponse response,
                            Long userId,
                            int status){
        outResult(request, response, "json", userService.changeStatus(userId, status));
    }

    @RequestMapping(value = "achieveHis.do")
    public String getAchieveHistory(ModelMap modelMap, Long userId,int role){
        User user = userService.getUserById(userId);

        List<AchieveHistory> achieveHistories = userService.getAchieveHistoryByUser(userId);
        modelMap.addAttribute("achieveHistoryList", achieveHistories);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("user", user);

        return "achievehis.jsp";
    }

    @RequestMapping(value = "orderHis.do")
    public String getOrderHistory(ModelMap modelMap, Long userId, int role){
        User user = userService.getUserById(userId);
        List<Order> orderList = userService.getOrderHistoryByUser(userId);
        modelMap.addAttribute("orderHistoryList", orderList);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("user", user);

        return "orderhis.jsp";
    }
}
