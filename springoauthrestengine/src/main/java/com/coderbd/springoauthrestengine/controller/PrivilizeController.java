package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.service.PrivilizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/privilize/")
public class PrivilizeController {
    @Autowired
    private PrivilizeService service;
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView getPrivilize() {
        ModelAndView modelAndView = new ModelAndView();
        Privilize privilize= new Privilize();
        modelAndView.addObject("privilize",privilize);
        modelAndView.setViewName("create-privilize");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView save(@Valid Privilize privilize, BindingResult bindingResult){
     //   privilize=new Privilize("READ");
        ModelAndView modelAndView = new ModelAndView();
        Privilize privilizeExit=service.isAlreadyExist(privilize.getPrivilizeName());
        System.out.println("===== "+privilize.getPrivilizeName());
        if(privilizeExit != null ){
            bindingResult.rejectValue("privilizeName", "error.privilize","You already have inserted this privilize");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("create-privilize");
        }else{
    service.save(privilize);
    modelAndView.addObject("successMessage","Insert Success");

    modelAndView.addObject("privilize", new Privilize());
     modelAndView.setViewName("create-privilize");
        }
        return modelAndView;
    }
}
