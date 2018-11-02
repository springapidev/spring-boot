package com.coderbd.springoauthrestengine.serviceImpl;

import com.coderbd.springoauthrestengine.entity.Tag;
import com.coderbd.springoauthrestengine.repo.TagRepo;
import com.coderbd.springoauthrestengine.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepo repo;

    @Override
    public Tag save(Tag tag) {
        return repo.saveAndFlush(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return repo.saveAndFlush(tag);
    }

    @Override
    public void delete(Long id) {
repo.deleteById(id);
    }

    @Override
    public Optional<Tag> getTag(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Tag> getAllTags(int page, int perPageRow) {
        return repo.findAll(PageRequest.of(page,perPageRow));
    }

    @Override
    public Tag isAlreadyExist(String tagName) {
        return repo.findByTagName(tagName);
    }
}
