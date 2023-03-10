package com.fightclub.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookListApplication {

    public static void main(String[] args) {

        boolean exit = true;

        List<Book> bookList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int variant = scanner.nextInt();
        Scanner author = new Scanner(System.in);
        Scanner title = new Scanner(System.in);

        Book newBook = new Book(author, title);

        System.out.println("Welcome  in our Library \n" +
                " Please select an option \n" +
                "No 1: add a new Book in our Library \n" +
                "No 2: delete Book from our Library  \n" +
                "No 3: List of all Books");


            if (variant == 1){
                System.out.println("Please Write Author and Title:");
                bookList.add(newBook);
            }

        }

    }

