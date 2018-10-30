package com.coderbd.springoauthrestengine.serviceImpl;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.repo.PrivilizeRepo;
import com.coderbd.springoauthrestengine.service.PrivilizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PrivilizeServiceImpl implements PrivilizeService {

    @Autowired
    PrivilizeRepo repo;
    @Override
    public Privilize save(Privilize privilize) {
        return repo.saveAndFlush(privilize);
    }

    @Override
    public Privilize update(Privilize privilize) {
        return repo.saveAndFlush(privilize);
    }

    @Override
    public void delete(Privilize privilize) {
        repo.delete(privilize);
    }

    @Override
    public Optional<Privilize> getPrivilize(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Privilize> getAllPrivilizes() {
        return repo.findAll();
    }

    @Override
    public Privilize isAlreadyExist(String privilizeName) {
        return repo.findByPrivilizeName(privilizeName);
    }
}
