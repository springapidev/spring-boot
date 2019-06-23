package com.coderbd.basicbootweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String home(Model model){
        return "home";
    }
    @GetMapping(value = "/about.bd")
    public String aboutDisplay(Model model){
        return "about";
    }
    @GetMapping(value = "/contact.php")
    public String contactDisplay(Model model){
        return "contact";
    }
}
