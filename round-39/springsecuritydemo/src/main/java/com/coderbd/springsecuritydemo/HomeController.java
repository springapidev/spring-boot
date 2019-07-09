package com.coderbd.springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    private StudentRepo repo;

    @GetMapping(value = "/insert")
    public void insertData(){
       Student student=new Student();
        student.setName(UUID.randomUUID().toString());
        student.setAge(25);
        this.repo.save(student);

    }

    @GetMapping(value = "/home")
    public String displayHome(Model model){
        model.addAttribute("list", this.repo.findAll());
        return "home";
    }


    @GetMapping(value = "/hello")
    public String displayHello(){
        return "hello";
    }
    @GetMapping(value = "/")
    public String displayIndex(){
        return "index";
    }
    @GetMapping(value = "/login")
    public String displayLogin(){
        return "login";
    }
}
