package cn.javakk;

import cn.javakk.util.TokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: javakk
 * @Date: 2019/5/14 15:12
 */

@Component
public class WebFilter extends ZuulFilter {

    private final String AUTH_FIX = "/auth";
    private final String POST_FIX = "/post";
    private final String SEARCH_FIX = "/search";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String filterType() {
        // 前置过滤器
        // 其他类型：route|post|error
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 优先级
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        ValueOperations opsForValue = redisTemplate.opsForValue();

        opsForValue.increment("zuul:view", 1);

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("DearOffer");
        requestContext.addZuulRequestHeader("DearOffer", token);

        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.contains(AUTH_FIX)) {
            opsForValue.increment("zuul:auth", 1);
        } else if (requestUrl.contains(POST_FIX)) {
            opsForValue.increment("zuul:post", 1);
        } else if (requestUrl.contains(SEARCH_FIX)) {
            opsForValue.increment("zuul:search", 1);
        }

//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        String url = request.getRequestURL().toString();
//        if (url.indexOf(AUTH_FIX) > 0) {
//            System.out.println("已认证资源" + url);
//            return null;
//        }
//
//
//        String token = request.getHeader("DearOffer");
//
//        if (StringUtils.isNotBlank(token)) {
//            Claims claims = null;
//            try {
//                claims = TokenUtil.parseToken(token);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            if (claims != null) {
//                request.setAttribute("user", claims.get("user"));
//                requestContext.addZuulRequestHeader("DearOffer", token);
//                // TODO:@JAVAKK 将用户存入Redis
//                System.out.println("通过认证");
//                return null;
//            }
//        }
//
//        // 网关停止跳转
//        requestContext.setSendZuulResponse(false);
//        // 返回客户端代码
//        requestContext.setResponseStatusCode(401);
//        // 返回客户端信息
//        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
//        requestContext.setResponseBody("No Access");
        return null;
    }
}
