package shop.core.services.fake.fake_item_generator;

import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;

public class HardcodedItemGeneratorImpl implements ItemGenerator {

    @Override
    public List<Item> createItems() {
        return List.of(new Item(/*1*/"Stop-and-Drop Suicide Booth", new BigDecimal("1000.00"), 1),
                new Item(/*2*/"Good News", new BigDecimal("7.00"), 7),
                new Item(/*3*/"Lightspeed Briefs", new BigDecimal("249.99"), 3),
                new Item(/*4*/"Mom's Old-Fashioned Robot Oil", new BigDecimal("9.99"), 150),
                new Item(/*5*/"Slurm", new BigDecimal("4.99"), 30),
                new Item(/*6*/"Morbo on Management", new BigDecimal("24.99"), 70),
                new Item(/*7*/"Blank Robot", new BigDecimal("17.50"), 10),
                new Item(/*8*/"iObey", new BigDecimal("0.01"), 1000),
                new Item(/*9*/"Captain's Handbook", new BigDecimal("2.50"), 5),
                new Item(/*10*/"Popplers", new BigDecimal("1.00"), 0));
    }

}
