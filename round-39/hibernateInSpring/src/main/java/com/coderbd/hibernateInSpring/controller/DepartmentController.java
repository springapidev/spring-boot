package com.coderbd.hibernateInSpring.controller;

import com.coderbd.hibernateInSpring.entity.Department;
import com.coderbd.hibernateInSpring.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepo repo;

    @GetMapping(value = "/")
    public String displayIndex(Model model){
        model.addAttribute("department",new Department());
        model.addAttribute("list",this.repo.findAll());

        return "index";
    }
    @PostMapping(value = "/")
    public String save(Model model, @Valid Department department, BindingResult result){
      if(result.hasErrors()){
          model.addAttribute("errMsg","Sometrhing Wrong");
      }else {
          this.repo.save(department);
          model.addAttribute("successMsg","Data Save Successfully");
          model.addAttribute("list",this.repo.findAll());
      }
        return "index";
    }
}
