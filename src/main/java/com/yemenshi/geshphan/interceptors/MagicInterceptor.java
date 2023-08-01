package com.yemenshi.geshphan.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class MagicInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String magic = request.getParameter("errorCode");
        if (StringUtils.hasText(magic)) {
            request.getRequestDispatcher("/magic/error").forward(request, response);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
