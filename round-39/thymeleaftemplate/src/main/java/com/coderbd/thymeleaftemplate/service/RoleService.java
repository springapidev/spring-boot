package com.coderbd.thymeleaftemplate.service;

import com.coderbd.thymeleaftemplate.entity.Role;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface RoleService {
    Role save(Role role);
    Role update(Role role);
    void delete(Long id);
    Optional<Role> getRole(Long id);
    Page<Role> getAllRole(int page, int perPageRow);
    Role isAlreadyExist(String roleName);

}
