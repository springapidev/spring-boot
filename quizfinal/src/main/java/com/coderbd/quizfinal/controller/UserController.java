package com.coderbd.quizfinal.controller;


import com.coderbd.quizfinal.entity.User;
import com.coderbd.quizfinal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
//@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepo repo;


    @GetMapping("/all")
    public List<User> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return repo.findAll(sortByCreatedAtDesc);
    }
    @PostMapping("/new")
    public User createTodo(@Valid @RequestBody User entity) {

        return repo.save(entity);
    }

    @GetMapping(value="/find/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return repo.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping(value="/findByUsernameOrEmail/{usernameOrEmail}")
    public ResponseEntity<User> getByUsernameOrEmail(@PathVariable("usernameOrEmail") String usernameOrEmail) {
        return repo.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value="/findByUsername/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username) {
        return repo.findByUsername(username)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id,
                                       @Valid @RequestBody User entity) {
        return repo.findById(id)
                .map(data -> {
                    data.setEmail(entity.getEmail());
                    data.setFirstName(entity.getFirstName());
                    data.setLastName(entity.getLastName());
                    User updatedEntity = repo.save(data);
                    return ResponseEntity.ok().body(updatedEntity);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/del/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") long id) {
        return repo.findById(id)
                .map(data -> {
                    repo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
