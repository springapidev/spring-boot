package com.coderbd.springoauthrestengine.controller;

import com.coderbd.springoauthrestengine.entity.Role;
import com.coderbd.springoauthrestengine.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService service;

    private static int currentPage = 1;
    private static int pageSize = 5;

    @RequestMapping(value = "/role/create", method = RequestMethod.GET)
    public ModelAndView getRole(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
       ModelAndView modelAndView = new ModelAndView();
        Role role = new Role();
        modelAndView.addObject("role", role);
        modelAndView.addObject("roles", service.getAllRole(page,perPage));
        modelAndView.setViewName("create-role");
        return modelAndView;
    }

    @RequestMapping(value = "/role/create", method = RequestMethod.POST)
    public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int perPage) {

        ModelAndView modelAndView = new ModelAndView();
        Role roleExit = service.isAlreadyExist(role.getRolename());
        System.out.println("===== " + role.getRolename());
        if (roleExit != null) {
            bindingResult.rejectValue("rolename", "error.role", "You already have inserted this role");
            modelAndView.addObject("roles", service.getAllRole(page,perPage));
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create-role");
        } else {
            if (role.getId() != null) {
                service.update(role);
                modelAndView.addObject("successMessage", "Update Success");

            } else {
                service.save(role);
                modelAndView.addObject("successMessage", "Insert Success");

            }



            modelAndView.addObject("role", new Role());
            modelAndView.addObject("roles", service.getAllRole(page,perPage));
            modelAndView.setViewName("create-role");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/role/edit/{id}", method = RequestMethod.GET)
    public String updateRole(@PathVariable Long id, Model model,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        Optional<Role> role1 = service.getRole(id);
        Role role=new Role();
        role.setRolename(role1.get().getRolename());
        role.setId(id);
        System.out.println("======"+role.getId()+", "+role.getRolename());
        model.addAttribute("role", role);
        model.addAttribute("roles", service.getAllRole(page,perPage));
        return "create-role";
    }

    @RequestMapping(value = "/role/del/{id}", method = RequestMethod.GET)
    public String delRole(@PathVariable Long id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "4") int perPage) {
        service.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Delete Success");
        modelAndView.addObject("roles", service.getAllRole(page,perPage));
        return "redirect:/role/create";
    }
}
