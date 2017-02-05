package com.hongchao.cms.controller;

import com.hongchao.cms.bean.AdminUser;
import com.hongchao.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by godlikehzj on 2017/1/24.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    public static final String user_info = "current_user_info";
    @Autowired
    private UserService userService;

    @RequestMapping("toLogin.do")
    public ModelAndView tologin(){
        return new ModelAndView("login.jsp");
    }

    @RequestMapping("login.do")
    public ModelAndView login(HttpServletRequest request,
                        HttpServletResponse response,
                        @Validated AdminUser user,
                        BindingResult result){
        ModelAndView success = new ModelAndView("redirect:/index.html");
        ModelAndView error = new ModelAndView("redirect:/login/toLogin.do");

        if (result.hasErrors()){
            return error;
        }

        AdminUser adminUser = userService.login(user);
        if (adminUser != null){
            request.getSession().setAttribute(user_info, adminUser);
            return success;
        }

        return error;
    }

    @RequestMapping("logout.do")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().removeAttribute(user_info);
        return new ModelAndView("redirect:/login/login.do");
    }
}
