package com.coderbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping(value = "/test")
    public ModelAndView test(){
ModelAndView mv=new ModelAndView();

mv.setViewName("test");
        return mv;
    }

    @RequestMapping("/interval")
    String interval() {

        return "interval";
    }
}
