package cn.javakk;

import cn.javakk.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 面经微服务启动类
 * @Author: javakk
 * @Date: 2019/3/23 17:29
 */

@SpringBootApplication
@CrossOrigin
public class InterviewExperienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewExperienceApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}