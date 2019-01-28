package com.coderbd.repo;

import com.coderbd.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    boolean findByEmailAddress(String emialAddress);
    boolean findByMobileNo(String mobileNo);
}
