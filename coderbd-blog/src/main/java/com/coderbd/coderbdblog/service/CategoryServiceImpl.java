package com.coderbd.coderbdblog.service;

import com.coderbd.coderbdblog.entity.Category;
import com.coderbd.coderbdblog.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo repo;

    @Override
    @Transactional(readOnly = true)
     public Category get(Long id) {
        return repo.getOne(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Category get(String name) {
        return repo.getByName(name);
    }

    @Transactional
    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Transactional
    @Override
    public void create(Category category) {
        repo.saveAndFlush(category);
    }

    @Transactional
    @Override
    public Category update(Category category) {
        return repo.saveAndFlush(category);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(Category category) {
        repo.delete(category);
    }
}
