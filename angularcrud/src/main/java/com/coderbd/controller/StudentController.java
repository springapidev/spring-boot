package com.coderbd.controller;

import com.coderbd.entity.Student;
import com.coderbd.repo.StudentRepo;
import com.coderbd.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/")
public class StudentController {
    public static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepo studentRepo;


    // -------------------Retrieve All Students---------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {
        List<Student> students = studentRepo.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    // -------------------Retrieve Single Student------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") long id) {
        LOGGER.info("Fetching Student with id {}", id);
       Student student = studentRepo.findById(id).orElse(new Student());
        if (student == null) {
            LOGGER.error("Student with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Student with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }


    // -------------------Create a Student-------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creating Student : {}", student);

        if (studentRepo.findByEmailAddress(student.getEmailAddress())) {
            LOGGER.error("Unable to create. A Student with email Address {} already exist", student.getEmailAddress());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Student with email " +
                    student.getEmailAddress() + " already exist."), HttpStatus.CONFLICT);
        }
        student.setRegiDate(new Date());
        studentRepo.save(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Student ------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        LOGGER.info("Updating Student with id {}", id);

        Student currentStudent = studentRepo.findById(student.getId()).orElse(new Student());

        if (currentStudent == null) {
            LOGGER.error("Unable to update. Student with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentStudent.setStudentName(student.getStudentName());
        currentStudent.setEmailAddress(student.getEmailAddress());
        currentStudent.setMobileNo(student.getMobileNo());
        currentStudent.setGender(student.getGender());
        currentStudent.setCompletedCourse(student.getCompletedCourse());
        currentStudent.setRound(student.getRound());
        currentStudent.setMsg(student.getMsg());
        studentRepo.save(currentStudent);
        return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
    }

    // ------------------- Delete a Student-----------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") long id) {
        LOGGER.info("Fetching & Deleting Student with id {}", id);

        Student student = studentRepo.findById(id).orElse(new Student());
        if (student == null) {
            LOGGER.error("Unable to delete. Student with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        studentRepo.deleteById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Students-----------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllStudents() {
        LOGGER.info("Deleting All Students");
        studentRepo.deleteAll();
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}
