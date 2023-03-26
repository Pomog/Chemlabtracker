package item_generator;

import item.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomItemGeneratorImpl implements ItemGenerator {

    private static final Integer ITEM_AMOUNT = 10;
    private static final Integer NAME_LENGTH = 5;
    private static final String AVAILABLE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private final Random random = new Random();

    @Override
    public List<Item> createItems() {
        List<Item> fakeItems = new ArrayList<>();
        for (int i = 0; i < ITEM_AMOUNT; i++) {
            fakeItems.add(createFakeItem());
        }
        return fakeItems;
    }

    private Item createFakeItem() {
        String name = getRandomName();
        BigDecimal price = new BigDecimal(random.nextDouble(10, 100)).setScale(2, RoundingMode.HALF_UP);
        Integer availableQuantity = random.nextInt(10) + 1;
        return new Item(name, price, availableQuantity);
    }

    private String getRandomName() {
        StringBuilder randomName = new StringBuilder(RandomItemGeneratorImpl.NAME_LENGTH);
        for (int i = 0; i < RandomItemGeneratorImpl.NAME_LENGTH; i++) {
            randomName.append(AVAILABLE_LETTERS.charAt(random.nextInt(RandomItemGeneratorImpl.AVAILABLE_LETTERS.length())));
        }
        return randomName.toString();
    }

}
