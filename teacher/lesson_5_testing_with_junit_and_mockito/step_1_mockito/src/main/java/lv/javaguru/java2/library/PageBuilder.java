package lv.javaguru.java2.library;

public class PageBuilder {

    private Integer number;
    private String content;

    public static PageBuilder createPage() {
        return new PageBuilder();
    }

    public Page build() {
        return new Page(number, content);
    }

    public PageBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public PageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

}

