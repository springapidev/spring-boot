package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
@Autowired
private CountryRepo countryRepo;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("countrylist",countryRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){

        return "add";
    }
}
