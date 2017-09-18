package lv.challenge.servlets.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Daniil on 13.06.2017.
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, getUrlByRole(authentication.getAuthorities()));
    }

    private String getUrlByRole(Collection<? extends GrantedAuthority> rolesSet) {

        if (rolesSet.contains(new UserAuthority("ROLE_TEAM_OWNER"))) {
            return "/menu";
        }
        if (rolesSet.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) return "/admin";
        if (rolesSet.contains(new SimpleGrantedAuthority("ROLE_LF_OPERATOR"))) return "/operator/lf";
        if (rolesSet.contains(new SimpleGrantedAuthority("ROLE_REGISTRAR"))) return "/operator/registrar";
        return "/";
    }
}