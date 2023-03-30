package console_ui.actions.shared;

import console_ui.UserCommunication;
import core.services.actions.shared.SignOutService;
import core.support.MutableLong;

import static org.mockito.Mockito.mock;

class SignOutUIActionTest {

    private final SignOutService mockSignOutService = mock(SignOutService.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final SignOutUIAction action =
            new SignOutUIAction(mockSignOutService, mockCurrentUserId, mockUserCommunication);

    //TODO test

}
