package com.coderbd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model){
        List<Country> countrylist = new ArrayList<>();
        countrylist.add(new Country(1L, "BD"));
        countrylist.add(new Country(2L, "Japan"));
        countrylist.add(new Country(3L, "USA"));
        model.addAttribute("countrylist",countrylist);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){

        return "add";
    }
}
