package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SigninController {
@Autowired
private UserRepository userRepo;
@Autowired
private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/signin")
    public String signin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return "redirect:/dashboard";
        }
        return "auth-signin";
    }

    @GetMapping(value = "/access-denied")
    public String showAccessDenied(){
        return "auth-access-denied";
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
