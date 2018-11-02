package com.coderbd.springoauthrestengine.config;

import com.coderbd.springoauthrestengine.entity.User;
import com.coderbd.springoauthrestengine.exception.ResourceNotFoundException;
import com.coderbd.springoauthrestengine.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let Visitor login with either username or email
        User user = userRepo.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail);
        if(user == null) {
          new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}