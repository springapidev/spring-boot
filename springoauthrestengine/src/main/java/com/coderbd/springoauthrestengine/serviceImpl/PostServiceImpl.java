package com.coderbd.springoauthrestengine.serviceImpl;

import com.coderbd.springoauthrestengine.entity.Post;
import com.coderbd.springoauthrestengine.repo.PostRepo;
import com.coderbd.springoauthrestengine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo repo;

    @Override
    public Post save(Post post) {
        return repo.saveAndFlush(post);
    }

    @Override
    public Post update(Post post) {
        return repo.saveAndFlush(post);
    }

    @Override
    public void delete(Long id) {
repo.deleteById(id);
    }

    @Override
    public Optional<Post> getPost(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Post> getAllPosts(int page, int perPageRow) {
        return repo.findAll(PageRequest.of(page,perPageRow));
    }

    @Override
    public Post isAlreadyExist(String title) {
        return repo.findByTitle(title);
    }
}
