package com.coderbd.quizfinal.repo;


import com.coderbd.quizfinal.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, Long>{
    Privilege findByName(String name);
}
