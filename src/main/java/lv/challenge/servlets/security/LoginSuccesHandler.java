package lv.challenge.servlets.security;

import lv.challenge.domain.users.User;
import lv.challenge.domain.users.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Daniil on 13.06.2017.
 */
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
        User user = userDetails.getUser();
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, getUrlByRole(user.getRoles()));
    }

    private String getUrlByRole(Set<UserRole> roleSet) {

        if (roleSet.contains(UserRole.ADMIN)) return "/admin";
        if (roleSet.contains(UserRole.USER)) return "/user";
        if (roleSet.contains(UserRole.TEAM_OWNER)) return "/menu";
        return "/";
    }
}