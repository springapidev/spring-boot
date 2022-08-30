package com.coderbd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilizeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);
}
