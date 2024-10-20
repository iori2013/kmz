package top.exfree.web.config.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        List<String> urlList = Arrays.asList("/error", "/login", "/swagger-ui.html","/webjars/**","/swagger-resources/**","/onload/**","/callback/**","/test/**","/open/**","/common/**");
//        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns(urlList);
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(Arrays.asList("/app/**"));
        super.addInterceptors(registry);

    }

}
