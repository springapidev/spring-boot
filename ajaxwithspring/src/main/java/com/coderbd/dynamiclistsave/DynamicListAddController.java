package com.coderbd.dynamiclistsave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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

    @GetMapping("/create")
    public String showCreateForm(Model model) {


//        for (int i = 1; i <= 3; i++) {
         //   booksForm.addBook(new Book());
       // }
        System.out.println("size at create: "+DynamicListRestController.booksForm.getBooks().size());
       model.addAttribute("form", DynamicListRestController.booksForm);
        return "createBook";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {
        System.out.println(form.getBooks().size());
        bookRepo.saveAll(form.getBooks());
        form.setBooks(new ArrayList<Book>());
        model.addAttribute("books", bookRepo.findAll());
        return "redirect:/all";
    }
}
