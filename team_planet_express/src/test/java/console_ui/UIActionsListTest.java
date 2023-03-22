package console_ui;

import database.Database;
import database.UserDatabase;
import domain.user.User;
import domain.user.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UIActionsListTest {

    private final Database mockDatabase = mock(Database.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final User mockUser = mock(User.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final UIActionsList uiActionsList = new UIActionsList(mockDatabase, mockUser, mockUserCommunication);

    @Test
    void shouldReturn8ActionsForNoId() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.empty());
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn8ActionsForGuest() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn7ActionsForCustomer() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.CUSTOMER);
        assertEquals(7, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn4ActionsForManager() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.MANAGER);
        assertEquals(4, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn3ActionsForAdmin() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        assertEquals(3, uiActionsList.getUIActionsListForUserRole().size());
    }

}
