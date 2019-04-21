package cn.javakk.config;

import cn.javakk.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: javakk
 * @Date: 2019/4/21 17:19
 */
@Configuration
public class WebFilterConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).
                 addPathPatterns("/**").
                 excludePathPatterns("/**/login");
    }
}
