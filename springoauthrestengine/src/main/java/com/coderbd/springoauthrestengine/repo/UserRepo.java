package com.coderbd.springoauthrestengine.repo;

import com.coderbd.springoauthrestengine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByMobile(String mobile);
    User findByUserName(String userName);
    User findByUserNameOrEmail(String userName, String email);
}
