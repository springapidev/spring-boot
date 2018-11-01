package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.service.PrivilizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class PrivilizeController {
    @Autowired
    private PrivilizeService service;

    private static int currentPage = 1;
    private static int pageSize = 5;

    @RequestMapping(value = "/privilize/create", method = RequestMethod.GET)
    public ModelAndView getPrivilize(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
       ModelAndView modelAndView = new ModelAndView();
        Privilize privilize = new Privilize();
        modelAndView.addObject("privilize", privilize);
        modelAndView.addObject("list", service.getAllPrivilizes(page,perPage));
        modelAndView.setViewName("create-privilize");
        return modelAndView;
    }

    @RequestMapping(value = "/privilize/create", method = RequestMethod.POST)
    public ModelAndView savePrivilize(@Valid Privilize privilize, BindingResult bindingResult,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        //   privilize=new Privilize("READ");
        ModelAndView modelAndView = new ModelAndView();
        Privilize privilizeExit = service.isAlreadyExist(privilize.getPrivilizeName());
        System.out.println("===== " + privilize.getPrivilizeName());
        if (privilizeExit != null) {
            bindingResult.rejectValue("privilizeName", "error.privilize", "You already have inserted this privilize");
            modelAndView.addObject("list", service.getAllPrivilizes(page,perPage));
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create-privilize");
        } else {
            if(privilize.getId() != null){
                service.update(privilize);
                modelAndView.addObject("successMessage", "Update Success");

            }else{
                service.save(privilize);
                modelAndView.addObject("successMessage", "Insert Success");

            }


            modelAndView.addObject("privilize", new Privilize());
            modelAndView.addObject("list", service.getAllPrivilizes(page,perPage));
            modelAndView.setViewName("create-privilize");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/privilize/edit/{id}", method = RequestMethod.GET)
    public String updatePrivilize(@PathVariable Long id, Model model,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        Optional<Privilize> privilize1 = service.getPrivilize(id);
        Privilize privilize=new Privilize();
        privilize.setPrivilizeName(privilize1.get().getPrivilizeName());
        privilize.setId(id);
        System.out.println("======"+privilize.getId()+", "+privilize.getPrivilizeName());
        model.addAttribute("privilize", privilize);
        model.addAttribute("list", service.getAllPrivilizes(page,perPage));
        return "create-privilize";
    }

    @RequestMapping(value = "/privilize/del/{id}", method = RequestMethod.GET)
    public String delPrivilize(@PathVariable Long id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        service.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Delete Success");
        modelAndView.addObject("list", service.getAllPrivilizes(page,perPage));
        return "redirect:/privilize/create";
    }
}
