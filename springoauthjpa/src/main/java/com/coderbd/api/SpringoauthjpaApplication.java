package com.coderbd.api;

import com.coderbd.api.config.CustomUserDetails;
import com.coderbd.api.entity.Role;
import com.coderbd.api.entity.User;
import com.coderbd.api.repo.UserRepo;
import com.coderbd.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

@SpringBootApplication
public class SpringoauthjpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringoauthjpaApplication.class, args);
	}


}
