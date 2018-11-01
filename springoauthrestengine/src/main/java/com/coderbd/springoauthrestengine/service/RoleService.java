package com.coderbd.springoauthrestengine.service;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.entity.Role;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Role save(Role role);
    Role update(Role role);
    void delete(Long id);
    Optional<Role> getRole(Long id);
    Page<Role> getAllRole(int page, int perPageRow);
    Role isAlreadyExist(String roleName);

}
