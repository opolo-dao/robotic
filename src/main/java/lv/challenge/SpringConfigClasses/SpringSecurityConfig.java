package lv.challenge.SpringConfigClasses;

import lv.challenge.domain.users.UserRole;
import lv.challenge.servlets.security.LoginSuccessHandler;
import lv.challenge.servlets.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by Daniil on 11.06.2017.
 */

@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        MyUserDetailService manager = new MyUserDetailService();
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Configuration
    @Order(1)
    public static class FieldControllersPartConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("mc")
                    .password("pass")
                    .roles("FIELD_CONTROLLER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/fieldcontroller")
                    .authorizeRequests().anyRequest().hasRole("FIELD_CONTROLLER")
                    .and()
                    .httpBasic();
        }
    }

    @Configuration
    @Order(2)
    public static class CommonPartConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        DaoAuthenticationProvider daoAuthenticationProvider;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password("123")
                    .roles("ADMIN")
                    .and()
                    .withUser("lf_operator")
                    .password("123")
                    .roles("LF_OPERATOR", "OPERATOR")
                    .and()
                    .withUser("registrar")
                    .roles("REGISTRAR", "OPERATOR")
                    .password("123")
                    .and()
                    .withUser("html")
                    .roles("HTML_EDITOR")
                    .password("html");
            auth.authenticationProvider(daoAuthenticationProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CharacterEncodingFilter charFilter = new CharacterEncodingFilter("UTF-8", true);
            http.authorizeRequests()
                    .antMatchers("/", "/login", "/css/**", "/js/**", "/pictures/**").permitAll()
                    .antMatchers("/admin/**").hasRole(UserRole.ADMIN.toString())
                    .antMatchers("/menu/**").hasRole(UserRole.TEAM_OWNER.toString())
                    .antMatchers("/operator/lf/**").hasAnyRole(UserRole.LF_OPERATOR.toString(), UserRole.ADMIN.toString())
                    .antMatchers("/operator/**").hasAnyRole(UserRole.OPERATOR.toString(), UserRole.ADMIN.toString())
                    .antMatchers("/operator/registrar/**").hasAnyRole(UserRole.REGISTRAR.toString(), UserRole.ADMIN.toString())
                    .antMatchers("/HTML_editor/**").hasRole(UserRole.HTML_EDITOR.toString())
                    .and()
                    .formLogin()
                    .loginPage("/login").successHandler(new LoginSuccessHandler())
                    .permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
            http.addFilterBefore(charFilter, CsrfFilter.class);
        }
    }

}
