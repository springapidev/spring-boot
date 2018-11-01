package com.coderbd.springoauthrestengine.service;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PrivilizeService {
    Privilize save(Privilize privilize);
    Privilize update(Privilize privilize);
    void delete(Long id);
    Optional<Privilize> getPrivilize(Long id);
    Page<Privilize> getAllPrivilizes(int page,int perPageRow);
    Privilize isAlreadyExist(String privilizeName);



}
