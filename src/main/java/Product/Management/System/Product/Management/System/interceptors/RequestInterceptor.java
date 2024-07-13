package Product.Management.System.Product.Management.System.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1 - pre handle.");
        System.out.println("METHOD type:" + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Servlet PATH: " + request.getServletPath());
        //check which controller method is requested
        if(handler instanceof HandlerMethod){
            //can be added different logics
            Class<?> controllerClass = ((HandlerMethod) handler).getBeanType();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            System.out.println("Controller name: " + controllerClass.getName());
            System.out.println("Method name:" + methodName);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("2 - post handle.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex != null){
            //exception handle part
            System.out.println("An error occured.");
        }
        System.out.println("3 - after completion.");
    }
}
