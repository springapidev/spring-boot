package com.coder.quizclient.security;


import com.coder.quizclient.payloads.*;
import com.coder.quizclient.service.LoginService;
import com.coder.quizclient.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    final static Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    JwtAuthenticationResponse response;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
           // Optional<UserDTO> userDTO=userService.findByUsername(authentication.getName());
            UserDTO dTO = new UserDTO();
            dTO.setUsername(authentication.getName());
            dTO.setPassword(authentication.getCredentials().toString());

            response = loginService.login(new LoginRequest(dTO.getUsername(), dTO.getPassword()));
            System.out.println("Token:::: "+response.getToken());
          /*  List<UserDTO> userDTOs = userService.getAll(response);
            System.out.println("Sayonti::::::::: "+userDTOs.size());

            for (UserDTO d : userDTOs) {
                if (d.getUsername().equals(dTO.getUsername())) {
                    dTO.setRoles(d.getRoles());
                }
            }*/
            /*Optional<UserDTO> uDTO=(Optional<UserDTO>)userService.findByUsernameOrEmail(dTO.getUsername(), dTO.getEmail());

            dTO.setRoles(uDTO.get().getRoles());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (AuthorityDTO role : dTO.getRoles()) {
                System.out.println("role name=====222=====" + role.getName());
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }*/
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
             grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
             grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuthorities);
            String username=authenticationToken.getName();
            System.out.println("username============ "+username);
        } catch (AuthenticationException auth) {
            LOGGER.error("ERROR for auth : " + auth);
            LOGGER.fatal("FATAL for auth : " + auth);
            LOGGER.debug("DEBUG for auth : " + auth);
        } catch (Exception e) {
            LOGGER.error("ERROR for : " + e);
            LOGGER.fatal("FATAL for : " + e);
            LOGGER.debug("DEBUG for : " + e);
        }
//        System.out.println("authenticationToken====== "+authenticationToken.getCredentials().toString());
        return authenticationToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public LoginResponse getResponse() {
        return response;
    }


}