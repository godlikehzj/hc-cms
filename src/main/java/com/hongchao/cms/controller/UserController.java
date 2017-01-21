package com.hongchao.cms.controller;

import com.hongchao.cms.bean.HouseInfo;
import com.hongchao.cms.bean.User;
import com.hongchao.cms.service.UserService;
import com.hongchao.cms.util.SysApiStatus;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @RequestMapping(value = "list.do")
    public String getList(ModelMap modelMap,int role, int status){
        List<User> users = userService.getUsers(role, status);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
        modelMap.addAttribute("status", status);
        modelMap.addAttribute("users", users);

        return "list.jsp";
    }

    @RequestMapping(value = "toAdd.do")
    public String toAddUser(ModelMap modelMap, int role){
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
        return "add.jsp";
    }

    @RequestMapping(value = "toEdit.do")
    public String toEditUser(ModelMap modelMap, long userId, int role){
        User user = userService.getUserById(userId);
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roleText", SysApiStatus.roles.get(role));
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
        outResult(request, response, "json", userService.addUser(role, mobile, name, houseIds));
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
}
