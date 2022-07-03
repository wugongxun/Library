package com.wgx.utils;

import com.wgx.pojo.Order;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(T bean, Map value) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String s, int defaultValue) {
        if (s == null || s == "") {
            return defaultValue;
        }else {
            return Integer.parseInt(s);
        }
    }
    public static double parseDouble(String s, double defaultValue) {
        if (s == null || s == "") {
            return defaultValue;
        }else {
            return Double.parseDouble(s);
        }
    }
    public static Cookie findCookie(Cookie[] cookies, String key) {
        if (cookies == null || cookies.length == 0 || key == null || key == "") {
            return null;
        }else {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie;
                }
            }
            return null;
        }
    }
}
