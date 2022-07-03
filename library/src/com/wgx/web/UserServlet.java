package com.wgx.web;

import com.google.gson.Gson;
import com.wgx.pojo.Cart;
import com.wgx.pojo.User;
import com.wgx.service.CartService;
import com.wgx.service.UserService;
import com.wgx.service.impl.CartServiceImpl;
import com.wgx.service.impl.UserServiceImpl;
import com.wgx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(new User(), req.getParameterMap());
        User loginUser = userService.login(user);
        if(loginUser == null) {
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            req.getSession().setAttribute("loginUser", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取参数
        String username = req.getParameter("username");
        String code = req.getParameter("code");
        String CAPTCHA = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().invalidate();
        //2. 检查验证码是否正确
        if (CAPTCHA != null && CAPTCHA.equalsIgnoreCase(code)) {
            //3. 检查用户名是否可用
            if (userService.existUsername(username)) {
                req.setAttribute("error", "用户名不可用");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            }else {
                User user = WebUtils.copyParamToBean(new User(), req.getParameterMap());
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        }else {
            req.setAttribute("error", "验证码错误");
            req.setAttribute("username", username);
            //回到注册页面
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
