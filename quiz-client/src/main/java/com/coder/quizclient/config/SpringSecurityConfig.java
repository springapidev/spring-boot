package com.coder.quizclient.config;


import com.coder.quizclient.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.coder.quizclient.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;


    public SpringSecurityConfig() {
        super();
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.do")
                .failureUrl("/login-error.do")
            .and()
                .logout()
                .logoutSuccessUrl("/login.do")
            .and()
                .authorizeRequests()
                .antMatchers("/super/**").hasRole("SUPER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/index.do").hasAnyRole("USER","ADMIN")
                .antMatchers("/createUser/**").hasRole("ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.do");

    }


   @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authProvider);
    }


}
