package com.coderbd.coderbdblog.repo;

import com.coderbd.coderbdblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getByName(String name);
}
