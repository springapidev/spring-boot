package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.entity.Category;
import com.coderbd.springoauthrestengine.service.CategoryService;
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
public class CategoryController {
    @Autowired
    private CategoryService service;

    private static int currentPage = 1;
    private static int pageSize = 5;

    @RequestMapping(value = "/category/create", method = RequestMethod.GET)
    public ModelAndView getCategory(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
       ModelAndView modelAndView = new ModelAndView();
        Category category = new Category();
        modelAndView.addObject("category", category);
        modelAndView.addObject("list", service.getAllCategories(page,perPage));
        modelAndView.setViewName("create-category");
        return modelAndView;
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public ModelAndView saveCategory(@Valid Category category, BindingResult bindingResult,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {

        ModelAndView modelAndView = new ModelAndView();
        Category categoryExit = service.isAlreadyExist(category.getCategoryName());
        System.out.println("===== " + category.getCategoryName());
        if (categoryExit != null) {
            bindingResult.rejectValue("categoryName", "error.category", "You already have inserted this category");
            modelAndView.addObject("list", service.getAllCategories(page,perPage));
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create-category");
        } else {
            if(category.getId() != null){
                service.update(category);
                modelAndView.addObject("successMessage", "Update Success");

            }else{
                service.save(category);
                modelAndView.addObject("successMessage", "Insert Success");

            }


            modelAndView.addObject("category", new Category());
            modelAndView.addObject("list", service.getAllCategories(page,perPage));
            modelAndView.setViewName("create-category");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/category/edit/{id}", method = RequestMethod.GET)
    public String updateCategory(@PathVariable Long id, Model model,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        Optional<Category> category1 = service.getCategory(id);
        Category category=new Category();
        category.setCategoryName(category1.get().getCategoryName());
        category.setId(id);
        System.out.println("======"+category.getId()+", "+category.getCategoryName());
        model.addAttribute("category", category);
        model.addAttribute("list", service.getAllCategories(page,perPage));
        return "create-category";
    }

    @RequestMapping(value = "/category/del/{id}", method = RequestMethod.GET)
    public String delCategory(@PathVariable Long id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        service.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Delete Success");
        modelAndView.addObject("list", service.getAllCategories(page,perPage));
        return "redirect:/category/create";
    }
}
