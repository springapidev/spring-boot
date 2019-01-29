package com.coderbd.onlinebankingrestapi.controller;

import com.coderbd.onlinebankingrestapi.entity.Account;
import com.coderbd.onlinebankingrestapi.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8008", maxAge = 360000)
public class AccountController {

    @Autowired
    private AccountRepo repo;

    @GetMapping(value = "/account-list")
    public List<Account> getAccountList(){
       return repo.findAll();
    }

    @GetMapping(value = "/insert")
    public void insertData(){
        Account account=new Account();
        account.setName("Mahedee");
        account.setMobileNo("01686005000");
        account.setAccountNo(104L);
        account.setOpeningDate(new Date());
        repo.save(account);
        System.out.println("Insert Success");
    }

}
