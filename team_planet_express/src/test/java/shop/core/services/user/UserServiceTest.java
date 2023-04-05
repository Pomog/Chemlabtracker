package shop.core.services.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartDatabase;
import shop.core.database.Database;
import shop.core.database.UserDatabase;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private Database mockDatabase;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private CartDatabase mockCartDatabase;
    @Mock private User mockUser;
    @Mock private Cart mockCart;

    @InjectMocks private UserService service;

    @Test
    void shouldSaveUserAndCart() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockUserDatabase.save(any(User.class))).thenReturn(mockUser);
        when(mockUser.getId()).thenReturn(1L);
        UserRecord record = new UserRecord("Name", "login name", "password", UserRole.GUEST);
        service.createUser(record);
        verify(mockUserDatabase).save(new User("Name", "login name", "password", UserRole.GUEST));
        verify(mockCartDatabase).save(new Cart(1L));
    }

    @Test
    void shouldReturnExistingGuest() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertTrue(service.findGuestWithOpenCart().isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNoGuest() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalForNoOpenCart() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

}
