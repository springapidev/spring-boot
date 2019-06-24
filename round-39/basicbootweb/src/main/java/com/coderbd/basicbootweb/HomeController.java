package com.coderbd.basicbootweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository repository;

    @GetMapping(value = "/")
    public String home(Model model) {
       model.addAttribute("student",new Student());
        return "home";
    }

    @PostMapping(value = "/")
    public String saveStudent(Model model, @Valid Student student) {
        this.repository.save(student);
        // model.addAttribute("success","Save Success!!!");
        return "redirect:/students";
    }


    @GetMapping(value = "/students")
    public String getStudentList(Model model) {
        List<Student> studentList = this.repository.findAll();
        model.addAttribute("slist", studentList);
        return "list";
    }

    @GetMapping(value = "/students2")
    public ModelAndView displayStudents() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentlist", this.repository.findAll());
        modelAndView.setViewName("list2");
        return modelAndView;
    }

    @GetMapping(value = "/students/{id}")
    public String getStudentById(Model model, @PathVariable("id") Long id){
        Student s = this.repository.getOne(id);
        model.addAttribute("stu",s );
        return "student";
    }

    @GetMapping(value = "/student")
    public String displayStudentById(Model model, @RequestParam(name = "studentId", required = false,
            defaultValue = "1") Long id){
        Student s = this.repository.getOne(id);
        model.addAttribute("stu",s );
        return "student2";
    }


    @GetMapping(value = "/about")
    public String aboutDisplay(Model model) {
        return "about";
    }

    @GetMapping(value = "/contact")
    public String contactDisplay(Model model) {
        return "contact";
    }
}
