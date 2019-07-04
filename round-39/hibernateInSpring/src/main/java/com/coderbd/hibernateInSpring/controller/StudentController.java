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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = "/stubydep")
    public String showStudentsByDepartment(Model model){
        Department department =new Department();
        department.setId(1L);
        model.addAttribute("listbydep",this.repo.findAllByDepartment(department));
        return "studentbydep";
    }


    @GetMapping(value = "/profile")
    public String showStudentsById(Model model, @RequestParam(value = "studentid", required = false,
            defaultValue = "1") Long id){
        model.addAttribute("slist",this.repo.findAll());
        model.addAttribute("stu",this.repo.getOne(id));
        return "profilepage";
    }

    @GetMapping(value = "/stubydepandgender")
    public String showStudentsByDepartmentAndGender(Model model, @RequestParam(value = "depid",
            required = false, defaultValue = "1") Long id, @RequestParam(value = "gender", required = false,
            defaultValue = "m") String gender){
        Department department =new Department();
        department.setId(id);
        model.addAttribute("listbydepandgen",this.repo.findAllByDepartmentAndGender(department,gender));
        model.addAttribute("deplist",this.departmentRepo.findAll());
        return "studentbydepandgen";
    }

    @GetMapping(value = "/age")
    public String showStudentsByAge(Model model, @RequestParam(value = "age", required = false,
            defaultValue = "20") int age){
        model.addAttribute("slist",this.repo.findAllByAgeGreaterThanEqual(age));
        return "agepage";
    }
}
