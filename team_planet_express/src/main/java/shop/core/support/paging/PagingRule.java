package shop.core.support.paging;

public class PagingRule {

    private Integer pageNumber;
    private final String pageSize;

    public PagingRule(Integer pageNumber, String pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void changePageNumber(Integer pageNumberDelta) {
        this.pageNumber += pageNumberDelta;
    }

}
