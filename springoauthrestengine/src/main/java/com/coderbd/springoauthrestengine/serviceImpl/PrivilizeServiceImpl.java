package com.coderbd.springoauthrestengine.serviceImpl;

import com.coderbd.springoauthrestengine.entity.Privilize;
import com.coderbd.springoauthrestengine.entity.Role;
import com.coderbd.springoauthrestengine.repo.PrivilizeRepo;
import com.coderbd.springoauthrestengine.service.PrivilizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;


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
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Privilize> getPrivilize(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Privilize> getAllPrivilizes(int page,int perPageRow) {
        return repo.findAll(PageRequest.of(page, perPageRow));
    }


    @Override
    public Privilize isAlreadyExist(String privilizeName) {
        return repo.findByPrivilizeName(privilizeName);
    }

}
