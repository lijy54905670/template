package com.xinyuan.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author liang
 */
@Configuration    //定义当前类为一个注解类，里面有一个或多个被@Bean注解的方法，这些方法会被扫描到Spring容器中
public class Swagger2 {

    /**
     * 创建一个api应用
     * @return
     */
    @Bean  //产生一个Bean对象，然后把这个Bean对象交给Spring容器管理
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())   //添加api的相关信息（标题，描述，服务地址url，版本）
                .select()             //返回一个ApiSelectBuilder实例
                .apis(RequestHandlerSelectors.basePackage("com.xinyuan.ms"))//将这个包路径下的所有使用swagger注解的接口全部暴露给swagger
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}