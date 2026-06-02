package com.example.campuserrand.config;

import com.example.campuserrand.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        System.out.println("当前请求路径：" + url);

        // 🔥 放行接口（非常关键）
        if (url.startsWith("/user") ||
                url.startsWith("/user/list") ||
                url.startsWith("/error")) {
            return true;
        }

        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }

        try {
            String phone = JwtUtil.parseToken(token);

            if (phone == null) {
                response.setStatus(401);
                return false;
            }

            return true;

        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}