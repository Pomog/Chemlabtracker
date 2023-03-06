package item_generator;

import data.Item;

import java.math.BigDecimal;
import java.util.List;

public class HardcodedItemGenerator implements ItemGenerator {

    @Override
    public List<Item> createItems() {
        return List.of(new Item("Stop-and-Drop Suicide Booth", new BigDecimal("1000.00"), 1),
                new Item("Good News", new BigDecimal("7.00"), 7),
                new Item("Lightspeed Briefs", new BigDecimal("249.99"), 3),
                new Item("Mom's Old-Fashioned Robot Oil", new BigDecimal("9.99"), 150),
                new Item("Slurm", new BigDecimal("4.99"), 30),
                new Item("Morbo on Management", new BigDecimal("24.99"), 70),
                new Item("Blank Robot", new BigDecimal("17.50"), 10),
                new Item("iObey", new BigDecimal("0.01"), 1000),
                new Item("Captain's Handbook", new BigDecimal("2.50"), 5),
                new Item("Popplers", new BigDecimal("1.00"), 0));
    }

}
