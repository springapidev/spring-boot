package com.coderbd.hibernateInSpring.controller;

import com.coderbd.hibernateInSpring.entity.Department;
import com.coderbd.hibernateInSpring.entity.Student;
import com.coderbd.hibernateInSpring.repo.DepartmentRepo;
import com.coderbd.hibernateInSpring.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo repo;
    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping(value = "/student")
    public String displayStudent(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("list",this.repo.findAll());
        model.addAttribute("deplist",this.departmentRepo.findAll());

        return "student";
    }
    @PostMapping(value = "/student")
    public String save(Model model, @Valid Student student, BindingResult result){
      if(result.hasErrors()){
          model.addAttribute("errMsg","Sometrhing Wrong");
      }else {
          this.repo.save(student);
          model.addAttribute("successMsg","Data Save Successfully");
          model.addAttribute("list",this.repo.findAll());
          model.addAttribute("deplist",this.departmentRepo.findAll());

      }
        return "student";
    }
}
