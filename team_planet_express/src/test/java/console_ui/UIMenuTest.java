package console_ui;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UIMenuTest {

    //TODO endless while is bad for testing
//    @Mock private UIActionsList mockUiActionsList;
//    @Mock private DatabaseAccessValidator mockDatabaseAccessValidator;
//    @Mock private UserCommunication mockUserCommunication;
//    @Mock private Database mockDatabase;
//    @Mock private UserDatabase mockUserDatabase;
//    @Mock private CurrentUserId mockCurrentUserId;
//    @Mock private User mockUser;
//
//    @InjectMocks private UIMenu uiMenu;
//
//    @Test
//    void shouldGetCurrentUserName() {
//        when(mockUiActionsList.getCurrentUserId()).thenReturn(mockCurrentUserId);
//        when(mockCurrentUserId.getValue()).thenReturn(1L);
//        when(mockDatabaseAccessValidator.getUserById(1L)).thenReturn(mockUser);
//        when(mockUiActionsList.getUIActionsListForUserRole()).thenReturn(List.of(new ExitUIAction(new ExitService(), mockUserCommunication)));
//        when(mockUserCommunication.getMenuActionNumber()).thenReturn(1);
//        uiMenu.startUI();
//        verify(mockDatabaseAccessValidator).getUserById(1L);
//    }
//
//    @Test
//    void shouldPrintMessageWithCurrentUserName() {
//
//        verify(mockUserCommunication).informUser("\r\nHello, User!");
//    }

    //TODO test

}
