package com.jon.filter;

import com.jon.service.util.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(1)
//@WebFilter(filterName = "sessionFilter", urlPatterns = {"/homePage","/reserve/*"})
@Repository
public class SessionFilter implements Filter {

    private static final Logger log = Logger.getLogger(SessionFilter.class);

    @Autowired
    private RedisService redisService;

    private String[] FILTER_WHITE = {"visittoonuch", "login", ".js", ".css", ".ico", ".jpg", ".png"};

    @Value("${redis.valid.time}")
    private int validTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String spath = request.getServletPath();
        boolean filterFlag = false;
        for (String str : FILTER_WHITE) {
            if (spath.indexOf(str) != -1) {
                filterFlag = true;
                break;
            }
        }
        if (filterFlag) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        String realIp = request.getRemoteHost();
        System.out.println(request.getRequestURI());
        if (StringUtils.isEmpty(realIp)) {
            realIp = request.getRemoteAddr();
        }


        if (redisService.exists(realIp)) {
            if ((int) redisService.get(realIp) < 50) {
                redisService.set(realIp, (int) redisService.get(realIp) + 1, Integer.toUnsignedLong(validTime));
                isLogin(session, response, request);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("visittoonuch");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            redisService.set(realIp, 1, Integer.toUnsignedLong(validTime));
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }


    private void isLogin(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (session.getAttribute("userInfo") == null) {
                String spath = request.getServletPath();
                if ("/homePage".indexOf(spath) > -1) {
                    return;
                }
                response.sendRedirect("/login");
            }
        } catch (Exception e) {
            log.error(e);
        }

        session.getAttributeNames();
    }
}
