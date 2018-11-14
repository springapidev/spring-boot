package com.topjal.ckeditorthymleaf.repo;

import com.topjal.ckeditorthymleaf.entity.ImageModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;


public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    Optional<ImageModel> getImageModelById(Long id);

}