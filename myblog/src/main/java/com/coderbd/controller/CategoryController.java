package com.coderbd.controller;

import com.coderbd.entity.Category;
import com.coderbd.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
public class CategoryController {
    @Autowired
    private CategoryRepo repo;

    @GetMapping(value = "/categories")
    public ResponseEntity<Page<Category>> getCategories(Pageable pageable){
        Page<Category> allCategories = this.repo.findAll(pageable);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
    @PostMapping(value = "/category")
    public ResponseEntity<?> save(@RequestBody Category category){
       this.repo.save(category);
        HttpHeaders httpHeaders =new HttpHeaders();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
                httpHeaders.setLocation(uri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
