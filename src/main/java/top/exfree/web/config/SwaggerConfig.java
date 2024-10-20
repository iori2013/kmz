package top.exfree.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import top.exfree.web.common.config.RuoYiConfig;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2的接口配置
 * 
 * @author kmz
 */
@Configuration
public class SwaggerConfig
{
    /** 是否开启swagger */
    @Value("${swagger.enabled}")
    private boolean enabled;


    @Bean
    public Docket appRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("对外模块")
                .apiInfo(apiInfo()).select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("top.exfree.web.controller.app"))
                .paths(PathSelectors.any())
                .build()
//                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("auth", "accessToken", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

//
//    /**
//     * 创建API
//     */
//    @Bean
//    public Docket createRestApi()
//    {
//        return new Docket(DocumentationType.OAS_30)
//                // 是否启用Swagger
//                .enable(enabled)
//                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
//                .apiInfo(apiInfo())
//                // 设置哪些接口暴露给Swagger展示
//                .select()
//                // 扫描所有有注解的api，用这种方式更灵活
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 扫描指定包中的swagger注解
//                //.apis(RequestHandlerSelectors.basePackage("top.exfree.web.project.tool.swagger"))
//                // 扫描所有 .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：管理系统_接口文档")
                // 描述
                .description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
                // 作者信息
                .contact(new Contact(RuoYiConfig.getName(), null, null))
                // 版本
                .version("版本号:" + RuoYiConfig.getVersion())
                .build();
    }
}
