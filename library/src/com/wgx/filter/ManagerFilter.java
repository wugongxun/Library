package com.wgx.filter;

import com.wgx.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        }else if ("manager".equals(loginUser.getJurisdiction())) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            request.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
