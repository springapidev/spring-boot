package com.coderbd.coderbdblog.service;

import com.coderbd.coderbdblog.entity.Category;

import java.util.List;

public interface CategoryService {
    Category get(Long id);

    Category get(String name);

    List<Category> getAll();

    void create(Category category);

    Category update(Category category);

    void delete(Long id);

    void delete(Category category);
}
