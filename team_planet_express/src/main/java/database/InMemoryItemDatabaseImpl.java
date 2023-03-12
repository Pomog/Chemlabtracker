package database;

import item.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InMemoryItemDatabaseImpl implements ItemDatabase {

    private Long nextId = 1L;
    private final List<Item> shopItems = new ArrayList<>();

    @Override
    public void save(Item item) {
        item.setId(nextId);
        nextId++;
        shopItems.add(item);
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setAvailableQuantity(newAvailableQuantity));
    }

    @Override
    public List<Item> getAllItems() {
        return shopItems;
    }

}
