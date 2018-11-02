package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.entity.Tag;
import com.coderbd.springoauthrestengine.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class TagController {
    @Autowired
    private TagService service;

    private static int currentPage = 1;
    private static int pageSize = 5;

    @RequestMapping(value = "/tag/create", method = RequestMethod.GET)
    public ModelAndView getTag(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
       ModelAndView modelAndView = new ModelAndView();
        Tag tag = new Tag();
        modelAndView.addObject("tag", tag);
        modelAndView.addObject("list", service.getAllTags(page,perPage));
        modelAndView.setViewName("create-tag");
        return modelAndView;
    }

    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public ModelAndView saveTag(@Valid Tag tag, BindingResult bindingResult,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {

        ModelAndView modelAndView = new ModelAndView();
        Tag tagExit = service.isAlreadyExist(tag.getTagName());
        System.out.println("===== " + tag.getTagName());
        if (tagExit != null) {
            bindingResult.rejectValue("tagName", "error.tag", "You already have inserted this Tag");
            modelAndView.addObject("list", service.getAllTags(page,perPage));
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create-tag");
        } else {
            if(tag.getId() != null){
                service.update(tag);
                modelAndView.addObject("successMessage", "Update Success");

            }else{
                service.save(tag);
                modelAndView.addObject("successMessage", "Insert Success");

            }


            modelAndView.addObject("tag", new Tag());
            modelAndView.addObject("list", service.getAllTags(page,perPage));
            modelAndView.setViewName("create-tag");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/tag/edit/{id}", method = RequestMethod.GET)
    public String updateTag(@PathVariable Long id, Model model,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        Optional<Tag> tag1 = service.getTag(id);
        Tag tag=new Tag();
        tag.setTagName(tag1.get().getTagName());
        tag.setId(id);
        System.out.println("======"+tag.getId()+", "+tag.getTagName());
        model.addAttribute("tag", tag);
        model.addAttribute("list",service.getAllTags(page,perPage));
        return "create-tag";
    }

    @RequestMapping(value = "/tag/del/{id}", method = RequestMethod.GET)
    public String delTag(@PathVariable Long id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        service.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Delete Success");
        modelAndView.addObject("list", service.getAllTags(page,perPage));
        return "redirect:/tag/create";
    }
}
