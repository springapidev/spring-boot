package com.coderbd.hibernateInSpring.repo;

import com.coderbd.hibernateInSpring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
