package com.topjal.ckeditorthymleaf.controller;

import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.topjal.ckeditorthymleaf.entity.ImageModel;
import com.topjal.ckeditorthymleaf.exception.StorageFileNotFoundException;
import com.topjal.ckeditorthymleaf.repo.ImageRepository;
import com.topjal.ckeditorthymleaf.repo.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileUploadController {
    @Autowired
    private ImageRepository imageRepository;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message",
                    "Select a file !");

        } else {

            storageService.store(file);
            ImageModel imageModel = new ImageModel();
            imageModel.setName(file.getName());
            imageModel.setType(file.getContentType());
            imageModel.setSize(file.getSize());
            try {
                imageModel.setPic(file.getBytes());
            } catch (IOException ie) {
                ie.printStackTrace();
            }

            imageRepository.saveAndFlush(imageModel);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        }
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
@GetMapping("/{id}")
    public String getImageModel(@PathVariable Long id, Model model) {
        Optional<ImageModel> imageModel = storageService.getImageModelById(id);

   imageModel.get().getPic();
    System.out.println("I am called"+imageModel.get().getPic().toString());
    return "displaySingleFromDb";
    }


    public String ckFileSaveToDatabase(@RequestPart MultipartFile upload){
String sourceName=upload.getOriginalFilename();


        return "";
    }

}

