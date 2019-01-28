package com.coderbd;

import com.coderbd.entity.User;
import com.coderbd.repo.UserRepo;
import com.coderbd.util.ImageOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D:/git/spring-boot/fileupload/src/main/resources/static/images/";




    @Autowired
    private UserRepo repo;

    @Autowired
    private ImageOptimizer imageOptimizer;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws IOException {
        ModelAndView mv=new ModelAndView();

        mv.addObject("list", repo.findAll());
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file1,
                                   RedirectAttributes redirectAttributes) throws IOException {

        if (file1.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");

            return "redirect:uploadStatus";
        }

        try {
            MultipartFile file = imageOptimizer.optimizeImage(UPLOADED_FOLDER,file1,0.5f,100,120);
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            User user = new User();
            user.setFileName(file.getOriginalFilename());
            user.setFileSize(file.getSize());
            user.setFile(file.getBytes());
            user.setFilePath("images/"+file.getOriginalFilename());
            user.setFileExtension(file.getContentType());
            repo.save(user);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "rs";
    }

}
