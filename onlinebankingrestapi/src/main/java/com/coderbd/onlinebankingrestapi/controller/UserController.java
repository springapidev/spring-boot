package com.coderbd.onlinebankingrestapi.controller;

import com.coderbd.onlinebankingrestapi.entity.User;
import com.coderbd.onlinebankingrestapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController(value = "/user")
@CrossOrigin(origins = "http://localhost:8008/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/insert")
    public void insertData(){
        User user=new User();
        user.setUsername("Zobayer");
        user.setEmail("zobayer@gmail.com");
        user.setAddress("BD");
        repo.save(user);

        System.out.println("Insert Success");
    }


    //-------------------Retrieve All Users--------------------------------------------------------

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = repo.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }





    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());

        if (repo.findByUsername(user.getUsername()) != null) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        repo.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }




}
