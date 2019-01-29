package com.coderbd.onlinebankingrestapi.repo;

import com.coderbd.onlinebankingrestapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
