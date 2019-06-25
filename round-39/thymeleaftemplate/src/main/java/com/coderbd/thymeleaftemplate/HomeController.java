package com.coderbd.thymeleaftemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String DisplayIndex(){
        return "index";
    }
    @GetMapping(value = "/about")
    public String DisplayAbout(){
        return "about";
    }
}
