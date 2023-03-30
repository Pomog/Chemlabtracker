package core.database;

import core.domain.item.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class InMemoryItemDatabaseImplTest {

    private final Item mockItem = mock(Item.class);

    private final InMemoryItemDatabaseImpl database = new InMemoryItemDatabaseImpl();

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockItem);
        assertEquals(1, database.getShopItems().size());
    }

    @Test
    void shouldReturnFoundItemById() {
        when(mockItem.getId()).thenReturn(1L);
        database.getShopItems().add(mockItem);
        assertTrue(database.findById(1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentItemById() {
        when(mockItem.getId()).thenReturn(2L);
        database.getShopItems().add(mockItem);
        assertTrue(database.findById(1L).isEmpty());
    }

    @Test
    void shouldReturnFoundItemByName() {
        when(mockItem.getName()).thenReturn("item");
        database.getShopItems().add(mockItem);
        assertTrue(database.findByName("item").isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentItemByName() {
        when(mockItem.getName()).thenReturn("different item");
        database.getShopItems().add(mockItem);
        assertTrue(database.findByName("item").isEmpty());
    }

    @Test
    void shouldChangeName() {
        when(mockItem.getId()).thenReturn(1L);
        database.getShopItems().add(mockItem);
        database.changeName(1L, "new name");
        verify(mockItem).setName("new name");
    }

    @Test
    void shouldChangePrice() {
        when(mockItem.getId()).thenReturn(1L);
        database.getShopItems().add(mockItem);
        database.changePrice(1L, new BigDecimal("10.10"));
        verify(mockItem).setPrice(new BigDecimal("10.10"));
    }

    @Test
    void shouldChangeAvailableQuantity() {
        when(mockItem.getId()).thenReturn(1L);
        database.getShopItems().add(mockItem);
        database.changeAvailableQuantity(1L, 10);
        verify(mockItem).setAvailableQuantity(10);
    }

    @Test
    void shouldReturn5Items() {
        database.getShopItems().add(mockItem);
        database.getShopItems().add(mockItem);
        database.getShopItems().add(mockItem);
        database.getShopItems().add(mockItem);
        database.getShopItems().add(mockItem);
        assertEquals(5, database.getAllItems().size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockItem);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

    //TODO tests for searches

}
