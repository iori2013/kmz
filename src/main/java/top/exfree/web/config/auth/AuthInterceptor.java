package top.exfree.web.config.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.exfree.web.common.utils.spring.SpringUtils;
import top.exfree.web.estate.absolute.TokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器

 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

//    @Resource
//    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(">>1111111111111）");

        TokenService tokenService = SpringUtils.getBean(TokenService.class);

        //校验token
        KmzUser kmzUser = tokenService.getLoginUser(request);

        if(null == kmzUser){
            return false;
        }

        final GlobeData globeData = new GlobeData();
        globeData.setUser(kmzUser);
        AuthInfoUtil.setGlobeData(globeData);
//支持跨域请求
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
        //支持跨域请求
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");
//        response.setHeader("Access-Control-Allow-Origin", "*");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("访问结束:" + AuthInfoUtil.getGlobeData().getUser());

        AuthInfoUtil.remove();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        //支持跨域请求
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");


        //        log.info("访问结束:" + AuthInfoUtil.getGlobeData());

    }

}
