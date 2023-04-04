package lv.javaguru.java2.library;

import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.java2.library.PageBuilder.createPage;

public class BookBuilder {

    private Long id;
    private String title;
    private String author;

    private List<Page> pages = new ArrayList<>();

    public static BookBuilder createBook() {
        return new BookBuilder();
    }

    public Book build() {
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPages(pages);
        return book;
    }

    public BookBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder withPage(Page page) {
        this.pages.add(page);
        return this;
    }


    public static void main(String[] args) {
        Book book1 = createBook()
                .withId(1L)
                .withAuthor("author")
                .withTitle("title")
                .withPage(createPage()
                        .withNumber(1)
                        .withContent("content")
                        .build())
                .withPage(createPage()
                        .withNumber(1)
                        .withContent("content")
                        .build())
                .build();

        Book book2 = BookBuilder.createBook()
                .withAuthor("author")
                .withTitle("title")
                .build();

        Book book3 = BookBuilder.createBook()
                .withTitle("title")
                .build();

        BookBuilder bookBuilder = BookBuilder.createBook();
        if (6 < 7) {
            bookBuilder.withId(4L);
        }
        Book book = bookBuilder.build();

    }

}
