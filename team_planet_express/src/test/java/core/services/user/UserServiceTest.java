package core.services.user;

import core.database.CartDatabase;
import core.database.Database;
import core.database.UserDatabase;
import core.domain.cart.Cart;
import core.domain.user.User;
import core.domain.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final Database mockDatabase = mock(Database.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final CartDatabase mockCartDatabase = mock(CartDatabase.class);
    private final User mockUser = mock(User.class);
    private final Cart mockCart = mock(Cart.class);

    private final UserService service = new UserService(mockDatabase);

    @BeforeEach
    void setupSharedMockBehaviour() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
    }

    @Test
    void shouldSaveUserAndCart() {
        when(mockUserDatabase.save(any(User.class))).thenReturn(mockUser);
        when(mockUser.getId()).thenReturn(1L);
        UserRecord record = new UserRecord("Name", "login name", "password", UserRole.GUEST);
        service.createUser(record);
        verify(mockUserDatabase).save(new User("Name", "login name", "password", UserRole.GUEST));
        verify(mockCartDatabase).save(new Cart(1L));
    }

    @Test
    void shouldReturnExistingGuest() {
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertTrue(service.findGuestWithOpenCart().isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNoGuest() {
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalForNoOpenCart() {
        when(mockUserDatabase.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

}
