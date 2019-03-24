package com.coderbd.dynamiclistsave;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DynamicListRestController {
   static BooksCreationDto booksForm = new BooksCreationDto();

      @GetMapping("/addrow")
    public List<Book> showCreateForm(Model model) {
        System.out.println("calll...............");
      // booksForm.addBook(new Book());
     //   System.out.println("Size at addrow url: "+ booksForm.getBooks().size());
       // return booksForm.getBooks();
        booksForm.addBook(new Book());
        System.out.println("books size: "+booksForm.getBooks().size());

        return booksForm.getBooks();
    }
}
