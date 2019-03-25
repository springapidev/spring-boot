package com.coderbd.dynamiclistsave;

import java.util.ArrayList;
import java.util.List;

public class BooksCreationDto {
    private List<Book> books=new ArrayList<>();
public BooksCreationDto(){
    this.books.add(new Book());
}
    // default and parameterized constructor

    public void addBook(Book book) {
        this.books.add(book);
    }
    public void removeBook(int index) {
        this.books.remove(index);
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
