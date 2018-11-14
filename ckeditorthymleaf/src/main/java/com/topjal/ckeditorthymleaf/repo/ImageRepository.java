package com.topjal.ckeditorthymleaf.repo;

import com.topjal.ckeditorthymleaf.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long>{
    Optional<ImageModel> getImageModelById(Long id);
}