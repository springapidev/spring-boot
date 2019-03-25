package com.coderbd.dynamiclistsave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DynamicListAddController {
    @Autowired
    private BookRepo bookRepo;
    BooksCreationDto booksForm = new BooksCreationDto();
    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "allbooks";
    }
    @GetMapping("/del/{index}")
    public String delFromList(@PathVariable("index") int index) {

        booksForm.removeBook(index);

        System.out.println("size at create: "+booksForm.getBooks().size());

        return "createBook";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {


//        for (int i = 1; i <= 3; i++) {
         //   booksForm.addBook(new Book());
       // }
        booksForm.addBook(new Book());

        System.out.println("size at create: "+booksForm.getBooks().size());
       model.addAttribute("form", booksForm);
        return "createBook";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {
        System.out.println(form.getBooks().size());
        bookRepo.saveAll(form.getBooks());
        model.addAttribute("form", new BooksCreationDto());

        model.addAttribute("books", bookRepo.findAll());
        return "redirect:/all";
    }
}
