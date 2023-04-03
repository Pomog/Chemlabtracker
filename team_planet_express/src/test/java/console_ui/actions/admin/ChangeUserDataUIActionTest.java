package console_ui.actions.admin;

import console_ui.UserCommunication;
import core.services.actions.admin.ChangeUserDataService;

import static org.mockito.Mockito.mock;

class ChangeUserDataUIActionTest {

    private final ChangeUserDataService mockChangeUserDataService = mock(ChangeUserDataService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final ChangeUserDataUIAction action =
            new ChangeUserDataUIAction(mockChangeUserDataService, mockUserCommunication);

    //TODO test

}
