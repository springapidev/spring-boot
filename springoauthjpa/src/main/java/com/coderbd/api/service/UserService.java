package com.coderbd.api.service;

import com.coderbd.api.entity.User;
import com.coderbd.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Bean
    private PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void saveUser(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
    }

    public User getSngleUser(String username){
        return userRepo.findByUsername(username);
    }

}
