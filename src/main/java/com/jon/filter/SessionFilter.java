package com.jon.filter;

import com.jon.util.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/","/homePage","/reserve/*"})
public class SessionFilter implements Filter {

    @Autowired
    private RedisService redisService;

    @Value("${redis.valid.time}")
    private int validTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String realIp = request.getRemoteHost();
        System.out.println(request.getRequestURI());
        if (StringUtils.isEmpty(realIp)) {
            realIp = request.getRemoteAddr();
        }

        if (redisService.exists(realIp)) {
            if ((int) redisService.get(realIp) < 50) {
                redisService.set(realIp, (int) redisService.get(realIp) + 1 , Integer.toUnsignedLong(validTime));
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("visittoonuch");
            }
        } else {
            redisService.set(realIp, 1, Integer.toUnsignedLong(validTime));
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
