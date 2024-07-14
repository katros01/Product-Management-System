package Product.Management.System.Product.Management.System.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().startsWith("/products") && request.getMethod().equalsIgnoreCase("POST")) {
            String role = (String) request.getSession().getAttribute("role");
            if (role == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("UnAuthorized");
                return false;
            } else if (!role.equals("seller")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access Denied: You do not have permission to perform this action.");
                return false;
            }
        }

        return true;
    }

}
