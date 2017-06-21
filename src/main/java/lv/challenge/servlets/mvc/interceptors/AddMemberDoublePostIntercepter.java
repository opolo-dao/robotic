package lv.challenge.servlets.mvc.interceptors;

import lv.challenge.servlets.mvc.StateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Daniil on 21.06.2017.
 */
public class AddMemberDoublePostIntercepter extends HandlerInterceptorAdapter {
    @Autowired
    StateData stateData;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (!request.getParameter("formToken").equals(stateData.getToken())) {
                response.sendRedirect("/robotic/menu#members");
                return false;
            } else {
                stateData.changeToken();
                return true;
            }
        }
        return true;
    }
}