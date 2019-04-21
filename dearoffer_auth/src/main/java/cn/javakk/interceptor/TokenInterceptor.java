package cn.javakk.interceptor;

import cn.javakk.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * @Author: javakk
 * @Date: 2019/4/21 17:17
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader("DearOffer");
        if (token != null) {
            Claims claims = TokenUtil.parseToken(token);
            if (claims != null) {
               request.setAttribute("user", claims.get("user"));
            }
        }
        return true;
    }
}
