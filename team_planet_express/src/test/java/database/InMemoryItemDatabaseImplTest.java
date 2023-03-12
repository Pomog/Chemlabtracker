package database;

import item.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
