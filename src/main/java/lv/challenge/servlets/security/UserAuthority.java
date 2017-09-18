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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthority that = (UserAuthority) o;

        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
