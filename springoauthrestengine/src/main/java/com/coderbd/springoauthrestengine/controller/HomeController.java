package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private PostService service;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int perPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","Hire Us to Build Your  Future ");
        modelAndView.addObject("list", service.getAllPosts(page, perPage));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/access-denied";
    }
}
