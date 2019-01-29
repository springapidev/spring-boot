package com.coderbd.controller;

import com.coderbd.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class HomeController {

    @RequestMapping("/")
    String index(ModelMap modal) {
        modal.addAttribute("title","Student CRUD Example");
        return "index";
    }

    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student=new Student();
        student.setId(1L);
        student.setStudentName("mr. Abc");
        student.setEmailAddress("abc@gmail.com");
        student.setMobileNo("01686000000");
        student.setRound("Round-37");
        student.setCompletedCourse(new String[]{"Js","Java","UML"});
        student.setRegiDate(new Date());
        student.setMsg("okkkkkk");

        Student student2=new Student();
        student2.setId(2L);
        student2.setStudentName("Mr. BD");
        student2.setEmailAddress("bd@gmail.com");
        student2.setMobileNo("01686000250");
        student2.setRound("Round-37");
        student2.setCompletedCourse(new String[]{"Js","Java","UML","HTML"});
        student2.setRegiDate(new Date());
        student2.setMsg("Yes");

        Student student3=new Student();
        student3.setId(3L);
        student3.setStudentName("Mr. BP");
        student3.setEmailAddress("bp@gmail.com");
        student3.setMobileNo("01688880250");
        student3.setRound("Round-37");
        student3.setCompletedCourse(new String[]{"UML","HTML"});
        student3.setRegiDate(new Date());
        student3.setMsg("Yes!!!");

        students.add(student);
        students.add(student2);
        students.add(student3);

        System.out.println("size: "+students.size());
        if (students.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

}
