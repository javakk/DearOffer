package cn.javakk;

import cn.javakk.crawler.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 公司微服务启动类
 * @Author: javakk
 * @Date: 2019/3/23 17:29
 */

@SpringBootApplication
@CrossOrigin
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
