package core.database;

import core.domain.item.Item;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<Item> findById(Long itemId) {
        return shopItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

    @Override
    public Optional<Item> findByName(String name) {
        return shopItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    @Override
    public void changeName(Long id, String newName) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setName(newName));
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setPrice(newPrice));
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

    //Method names should not start with capital letters
    //I would call params as nameToSearch etc.
    //Tests missing
    @Override
    public List<Item> searchByName(String itemName) {
        //why do you format this in such a weird way?
        return shopItems.stream().filter(
                //What's the subSequence for?
                item -> item.getName().contains(itemName.subSequence(0, itemName.length()))
        ).collect(Collectors.toList());
    }

    //Tests missing
    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
        return shopItems.stream().filter(
                item -> item.getName().contains(itemName.subSequence(0, itemName.length())) &&
                        item.getPrice().equals(price)
        ).collect(Collectors.toList());

    }


}
