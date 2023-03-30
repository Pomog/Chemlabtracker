package console_ui.actions.shared;

import console_ui.UserCommunication;
import core.services.actions.shared.SearchItemService;

import static org.mockito.Mockito.mock;

class SearchItemUIActionTest {

    private final SearchItemService mockSearchItemService = mock(SearchItemService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final SearchItemUIAction action =
            new SearchItemUIAction(mockSearchItemService, mockUserCommunication);

    //TODO test

}
