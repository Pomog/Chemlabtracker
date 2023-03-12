package database;

import item.Item;

import java.util.List;

public interface ItemDatabase {

    void save(Item item);

    void changeAvailableQuantity(Long id, Integer newAvailableQuantity);

    List<Item> getAllItems();

}
