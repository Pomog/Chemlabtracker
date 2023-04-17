package shop.console_ui.item_list;

import shop.console_ui.UserCommunication;
import shop.core.support.paging.PageNavigation;
import shop.core.support.paging.PagingRule;

public class PagingUIElement {

    private static final String PROMPT_TOPIC_PAGE_SIZE = "number of items to be displayed per page: ";
    private static final String PROMPT_PAGE_NAVIGATION = "to navigate paged results: ";
    private static final String COMA = ", ";
    private static final String BLANK = " ";

    private final UserCommunication userCommunication;

    public PagingUIElement(UserCommunication userCommunication) {
        this.userCommunication = userCommunication;
    }

    public PagingRule getPagingRule() {
        String pageSize = userCommunication.requestInput(PROMPT_TOPIC_PAGE_SIZE);
        return (pageSize == null || pageSize.isBlank())
                ? null
                : new PagingRule(1, pageSize);
    }

    public boolean continuePagingThrough(PagingRule pagingRule, Integer totalFoundItemCount) {
        return pagingRule != null && Integer.parseInt(pagingRule.getPageSize()) < totalFoundItemCount && userContinuesPaging(pagingRule, totalFoundItemCount);
    }

    private boolean userContinuesPaging(PagingRule pagingRule, Integer totalFoundItemCount) {
        String pageNavigationOptions = getPageNavigationOptions(pagingRule.getPageNumber(), Integer.parseInt(pagingRule.getPageSize()), totalFoundItemCount);
        int pageNumberDelta = 0;
        do {
            String userInput = userCommunication.requestInput(pageNavigationOptions + PROMPT_PAGE_NAVIGATION);
            if (PageNavigation.NEXT.getText().equalsIgnoreCase(userInput) &&
                    pageNavigationOptions.contains(PageNavigation.NEXT.getText())) {
                pageNumberDelta = 1;
            } else if (PageNavigation.BACK.getText().equalsIgnoreCase(userInput) &&
                    pageNavigationOptions.contains(PageNavigation.BACK.getText())) {
                pageNumberDelta = -1;
            } else if (PageNavigation.EXIT.getText().equalsIgnoreCase(userInput)) {
                return false;
            }
        } while (!isUserInputValid(pageNumberDelta));
        changePage(pagingRule, pageNumberDelta);
        return true;
    }

    private String getPageNavigationOptions(Integer pageNumber, Integer pageSize, Integer totalFoundItemCount) {
        StringBuilder promptOptions = new StringBuilder();
        if (pageNumber > 1) {
            promptOptions.append(PageNavigation.BACK.getText()).append(COMA);
        }
        if (totalFoundItemCount > pageNumber * pageSize) {
            promptOptions.append(PageNavigation.NEXT.getText()).append(COMA);
        }
        promptOptions.append(PageNavigation.EXIT.getText()).append(BLANK);
        return promptOptions.toString();
    }

    private boolean isUserInputValid(Integer pageNumberDelta) {
        return pageNumberDelta == -1 || pageNumberDelta == 1;
    }

    private void changePage(PagingRule pagingRule, Integer pageNumberDelta) {
        pagingRule.changePageNumber(pageNumberDelta);
    }

}
