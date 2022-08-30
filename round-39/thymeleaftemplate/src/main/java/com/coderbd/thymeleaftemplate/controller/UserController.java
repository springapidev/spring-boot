package com.coderbd.thymeleaftemplate.controller;

import com.coderbd.thymeleaftemplate.entity.Role;
import com.coderbd.thymeleaftemplate.entity.User;
import com.coderbd.thymeleaftemplate.repo.RoleRepo;
import com.coderbd.thymeleaftemplate.repo.UserRepo;
import com.coderbd.thymeleaftemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String UPLOAD_FOLDER = "src/main/resources/static/upload/";

    @GetMapping(value = "/signup")
    public String displayUser(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("rolelist" , this.roleRepo.findAll());
        return "user";
    }
    @PostMapping(value = "signup")
    public String signUp(@Valid User user, BindingResult result,
                         @RequestParam("photo")MultipartFile photo, Model model) throws IOException{
      if(result.hasErrors()){
          model.addAttribute("errMsg","Something Wrong");
      }
        user.setPhotoPath("/upload/"+photo.getOriginalFilename());
       //for file upload
        byte[] bytes = photo.getBytes();
        Path path = Paths.get(UPLOAD_FOLDER+photo.getOriginalFilename());
        Files.write(path,bytes);
        // upload end
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
        model.addAttribute("sucMsg","Success");
        return "user";
    }

}
