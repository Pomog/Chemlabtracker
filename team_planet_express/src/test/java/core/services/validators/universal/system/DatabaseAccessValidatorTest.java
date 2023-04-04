package core.services.validators.universal.system;

import core.database.*;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.domain.user.User;
import core.services.exception.ServiceMissingDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseAccessValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private ItemDatabase mockItemDatabase;
    @Mock private CartDatabase mockCartDatabase;
    @Mock private CartItemDatabase mockCartItemDatabase;
    @Mock private User mockUser;
    @Mock private Item mockItem;
    @Mock private Cart mockCart;
    @Mock private CartItem mockCartItem;

    @InjectMocks private DatabaseAccessValidator databaseAccessValidator;

    @Test
    void shouldReturnUserById() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessValidator.getUserById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserById() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getUserById(1L));
    }

    @Test
    void shouldReturnUserByLoginName() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserByLoginName() {
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldReturnItemById() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessValidator.getItemById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemById() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getItemById(1L));
    }

    @Test
    void shouldReturnItemByName() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("item name")).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemByName() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("item name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldReturnOpenCartByUserId() {
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertNotNull(databaseAccessValidator.getOpenCartByUserId(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForOpenCartByUserId() {
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getOpenCartByUserId(1L));
    }

    @Test
    void shouldReturnCartItemByCartIdAndItemId() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.of(mockCartItem));
        assertNotNull(databaseAccessValidator.getCartItemByCartIdAndItemId(1L, 1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForCartItemByCartIdAndItemId() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getCartItemByCartIdAndItemId(1L, 1L));
    }

}
