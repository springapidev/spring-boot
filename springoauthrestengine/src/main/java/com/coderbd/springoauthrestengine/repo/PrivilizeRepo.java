package com.coderbd.springoauthrestengine.repo;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrivilizeRepo extends JpaRepository<Privilize, Long> {
    Privilize findByPrivilizeName(String privilizeName);


}
