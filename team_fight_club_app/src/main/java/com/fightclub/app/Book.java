package com.fightclub.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {

   private final Scanner author;
    private final Scanner title;

    List<Book> bookList;

    public Book(Scanner author, Scanner title) {
        this.author = author;
        this.title = title;
    }

    public void addBook(Book book) {

        if (bookList == null){
            bookList = new ArrayList<>();
        }
        bookList.add(book);
    }

    public void deleteBook(Book book) {

        bookList.remove(book);
    }

    public void printBook() {

        for (Book books : bookList) {
            System.out.println(books);
        }
    }
}
