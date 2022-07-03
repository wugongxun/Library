package com.wgx.filter;

import com.wgx.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
