package database;

import domain.item.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Item> findByName(String name) {
        return shopItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return shopItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
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
