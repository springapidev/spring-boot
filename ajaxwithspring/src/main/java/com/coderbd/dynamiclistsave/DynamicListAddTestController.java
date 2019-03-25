package com.coderbd.dynamiclistsave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DynamicListAddTestController {
    @Autowired
    private BookRepo bookRepo;

public static List<Book> books=new ArrayList();;

    @GetMapping("/cr")
    public String showCreateForm(List<Book> list, Model model) {
        model.addAttribute("list",list);
        return "create";
    }

    @PostMapping("/cr")
    public String saveBooks(@ModelAttribute BooksCreationDto form) {
        System.out.println("form.books size: "+form.getBooks().size());
        bookRepo.saveAll(form.getBooks());
        return "create";
    }

    public List<Book> getBooks() {
        return books;
    }


}
