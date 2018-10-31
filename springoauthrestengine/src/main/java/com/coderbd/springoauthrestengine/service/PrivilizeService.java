package com.coderbd.springoauthrestengine.service;

import com.coderbd.springoauthrestengine.entity.Privilize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PrivilizeService {
    Privilize save(Privilize privilize);
    Privilize update(Privilize privilize);
    void delete(Long id);
    Optional<Privilize> getPrivilize(Long id);
    List<Privilize> getAllPrivilizes();
    Privilize isAlreadyExist(String privilizeName);

}
