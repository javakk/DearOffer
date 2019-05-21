package cn.javakk;

import cn.javakk.crawler.util.TokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @Author: javakk
 * @Date: 2019/5/14 15:10
 */

@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }

    @Bean
    public TokenUtil tokenUtil(){
        return new TokenUtil();
    }
}
