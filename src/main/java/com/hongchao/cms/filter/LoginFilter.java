package com.hongchao.cms.filter;

import com.hongchao.cms.bean.AdminUser;
import com.hongchao.cms.controller.LoginController;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by godlikehzj on 2017/1/24.
 */
@Component
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;

        String path = servletRequest.getRequestURI();
        if (path.contains("/login.do") || path.contains("/toLogin.do")){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        AdminUser user = (AdminUser)servletRequest.getSession().getAttribute(LoginController.user_info);
        if (user == null){
            servletResponse.sendRedirect(servletRequest.getContextPath() + "/login/toLogin.do");
        }else{
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy(){

    }
}
