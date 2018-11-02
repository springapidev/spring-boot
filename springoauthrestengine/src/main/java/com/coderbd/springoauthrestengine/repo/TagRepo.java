package com.coderbd.springoauthrestengine.repo;

import com.coderbd.springoauthrestengine.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByTagName(String tagName);
}
