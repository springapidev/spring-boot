package com.coderbd.springoauthrestengine.service;

import com.coderbd.springoauthrestengine.entity.Category;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    Category update(Category category);
    void delete(Long id);
    Optional<Category> getCategory(Long id);
    Page<Category> getAllCategories(int page, int perPageRow);
    Category isAlreadyExist(String categoryName);



}
