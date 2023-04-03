package console_ui.actions.customer;

import console_ui.UserCommunication;
import core.services.actions.customer.BuyService;
import core.support.MutableLong;

import static org.mockito.Mockito.mock;

class BuyUIActionTest {

    private final BuyService mockBuyService = mock(BuyService.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final BuyUIAction action =
            new BuyUIAction(mockBuyService, mockCurrentUserId, mockUserCommunication);

    //TODO test

}
