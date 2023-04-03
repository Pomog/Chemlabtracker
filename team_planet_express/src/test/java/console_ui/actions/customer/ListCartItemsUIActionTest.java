package console_ui.actions.customer;

import console_ui.UserCommunication;
import core.services.actions.customer.ListCartItemsService;
import core.support.MutableLong;

import static org.mockito.Mockito.mock;

class ListCartItemsUIActionTest {

    private final ListCartItemsService mockListCartItemsService = mock(ListCartItemsService.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final ListCartItemsUIAction action =
            new ListCartItemsUIAction(mockListCartItemsService, mockCurrentUserId, mockUserCommunication);

    //TODO test

}
