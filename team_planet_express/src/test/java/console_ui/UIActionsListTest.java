package console_ui;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UIActionsListTest {

    @Mock private Database mockDatabase;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private MutableLong mockCurrentUserId;
    @Mock private User mockUser;

    @InjectMocks private UIActionsList uiActionsList;

    @Test
    void shouldReturn8ActionsForNoId() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.empty());
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn8ActionsForGuest() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn7ActionsForCustomer() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.CUSTOMER);
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn4ActionsForManager() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.MANAGER);
        assertEquals(5, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn3ActionsForAdmin() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        assertEquals(4, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturnCurrentUserName() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        uiActionsList.getCurrentUserName();
        verify(mockUser).getName();
    }

    @Test
    void shouldThrowExceptionForMissingOptional() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, uiActionsList::getCurrentUserName);
    }

}
