package shop.core.support.paging;

public enum PageNavigation {

    BACK("back"),
    NEXT("next"),
    EXIT("exit");

    private final String text;

    PageNavigation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
