package com.coderbd.springoauthrestengine.serviceImpl;

import com.coderbd.springoauthrestengine.entity.Category;
import com.coderbd.springoauthrestengine.repo.CategoryRepo;
import com.coderbd.springoauthrestengine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo repo;
    @Override
    public Category save(Category category) {
        return repo.saveAndFlush(category);
    }

    @Override
    public Category update(Category category) {
        return repo.saveAndFlush(category);
    }

    @Override
    public void delete(Long id) {
repo.deleteById(id);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Category> getAllCategories(int page, int perPageRow) {
        return repo.findAll(PageRequest.of(page, perPageRow));
    }

    @Override
    public Category isAlreadyExist(String categoryName) {
        return repo.findByCategoryName(categoryName);
    }
}
