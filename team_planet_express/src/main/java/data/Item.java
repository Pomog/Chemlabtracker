package data;

import java.math.BigDecimal;

public class Item {

    private static Long idCounter = 1L;

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private Integer quantityAvailable;

    public Item(String name, BigDecimal price, Integer quantityAvailable) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public static void setIdCounter(Long idCounter) {
        Item.idCounter = idCounter;
    }

    public void decreaseQuantityAvailable(Integer amount) {
        quantityAvailable -= amount;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", available quantity=" + quantityAvailable;
    }

}
