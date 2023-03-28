package core.database;

import core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemDatabase {

    void save(Item item);

    Optional<Item> findById(Long itemId);

    Optional<Item> findByName(String name);

    void changeName(Long id, String newName);

    void changePrice(Long id, BigDecimal newPrice);

    void changeAvailableQuantity(Long id, Integer newAvailableQuantity);

    List<Item> getAllItems();

    List<Item> SearchByName(String itemName);

    List<Item> SearchByNameAndPrice(String itemName, BigDecimal price);
}
