package com.coderbd.repo;

import com.coderbd.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {
}
