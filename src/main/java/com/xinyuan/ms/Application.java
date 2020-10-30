package com.xinyuan.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author liang
 */
@EnableCaching  //开启redis缓存
@EnableSwagger2 //通过@EnableSwagger2注解来启用Swagger2。
@SpringBootApplication //目的是开启自动配置   -----  等价于同时组合使用@EnableAutoConfiguration（自动配置），@ComponentScan（组件扫描），@SpringBootConfiguration（声明当前类是注解类）
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
