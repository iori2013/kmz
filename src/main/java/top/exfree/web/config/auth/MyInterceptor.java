//package top.exfree.web.config.auth;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class MyInterceptor implements HandlerInterceptor {
//
//    private final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//
//
//        logger.info("请求处理之前>>>param>>>>>>>>>" + request.getRequestURI() + JSON.toJSONString(getAllRequestParam(request)));
//
//        logger.info("请求处理之前>>>token >>>>>>>>>" + request.getHeader("token"));
//
//        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        System.out.println(">>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
//    }
//
//
//    private Map getAllRequestParam(final HttpServletRequest request) {
//        Map res=new HashMap();
//
//        Enumeration temp=request.getParameterNames();
//
//        if (null !=temp) {
//            while (temp.hasMoreElements()) {
//                String en=(String) temp.nextElement();
//                String value=request.getParameter(en);
//                res.put(en, value);
//
//                //如果字段的值为空，判断若值为空，则删除这个字段>
//                if (null==res.get(en) || "".equals(res.get(en))) {
//                    res.remove(en);
//                }
//            }
//
//        }
//
//        return res;
//
//    }
//
//}
