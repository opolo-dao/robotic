package lv.challenge.servlets.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Daniil on 12.06.2017.
 */
public class UserAuthority implements GrantedAuthority {
    public UserAuthority(String role) {
        this.role = role;
    }

    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
