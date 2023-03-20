package database;

import domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDatabase {

    void save(Item item);

    Optional<Item> findById(Long itemId);

    Optional<Item> findByName(String name);

    void changeAvailableQuantity(Long id, Integer newAvailableQuantity);

    List<Item> getAllItems();

}
