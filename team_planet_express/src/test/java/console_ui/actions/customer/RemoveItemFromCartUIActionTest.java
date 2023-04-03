package console_ui.actions.customer;

import console_ui.UserCommunication;
import core.services.actions.customer.RemoveItemFromCartService;
import core.support.MutableLong;

import static org.mockito.Mockito.mock;

class RemoveItemFromCartUIActionTest {

    private final RemoveItemFromCartService mockRemoveItemFromCartService = mock(RemoveItemFromCartService.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final RemoveItemFromCartUIAction action =
            new RemoveItemFromCartUIAction(mockRemoveItemFromCartService, mockCurrentUserId, mockUserCommunication);

    //TODO test

}
