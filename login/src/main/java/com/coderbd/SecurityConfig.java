package com.coderbd;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@EnableWebSecurity
@Slf4j
@EnableJpaRepositories(basePackageClasses = com.coderbd.UserRepository.class)
@Configuration
public class SecurityConfig {
    private final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    MyUserDetailsService customUserDetailsService;
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests((authz) ->
                authz.antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")


        );
        httpSecurity.authorizeRequests().antMatchers("/user").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/signin")
                .defaultSuccessUrl("/dashboard")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                .logoutSuccessUrl("/signin?signout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );
        getList(httpSecurity);
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/templates/**", "/static/**");
    }

    public void getList(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/read").hasAuthority("READ_PRIVILEGE");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/write").hasAuthority("WRITE_PRIVILEGE");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/user").hasRole("USER");

    }

}