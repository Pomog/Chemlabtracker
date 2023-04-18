package shop.core.domain.item;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer availableQuantity;

    public Item(String name, BigDecimal price, Integer availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity;
    }

}
