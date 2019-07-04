package com.coderbd.hibernateInSpring.repo;

import com.coderbd.hibernateInSpring.entity.Department;
import com.coderbd.hibernateInSpring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
Iterable<Student> findAllByDepartment(Department department);
Iterable<Student> findAllByDepartmentAndGender(Department department,String gender);
long countAllByDepartmentAndGender(Department department,String gender);
Iterable<Student> findAllByAgeGreaterThanEqual(int age);
    Iterable<Student> findAllByDepartmentOrderByDepartmentDesc(Department department);
}
