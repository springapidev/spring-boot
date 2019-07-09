package com.coderbd.thymeleaftemplate.service;


import com.coderbd.thymeleaftemplate.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User save(User user);
    User update(User user);
    void delete(Long id);
    Optional<User> getUser(Long id);

    Page<User> getAllUsers(int page, int perPageRow);
    User isEmailAlreadyExist(String email);
    User isMobileAlreadyExist(String mobile);
    User isUserNameAlreadyExist(String userName);
    User findByUserNameAndPassword(String userName, String password);

}
