package com.coderbd.springoauthrestengine.repo;

import com.coderbd.springoauthrestengine.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
}
