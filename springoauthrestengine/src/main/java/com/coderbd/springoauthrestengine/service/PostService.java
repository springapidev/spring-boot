package com.coderbd.springoauthrestengine.service;

import com.coderbd.springoauthrestengine.entity.Post;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {
    Post save(Post post);
    Post update(Post post);
    void delete(Long id);
    Optional<Post> getPost(Long id);
    Page<Post> getAllPosts(int page, int perPageRow);
    Post isAlreadyExist(String title);



}
