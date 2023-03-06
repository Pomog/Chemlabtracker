package data;

public class OrderedItem {

    private final Item item;
    private Integer quantityOrdered;

    public OrderedItem(Item item, Integer quantityOrdered) {
        this.item = item;
        this.quantityOrdered = quantityOrdered;
    }

    @Override
    public String toString() {
        return "item=" + item +
                ", ordered quantity=" + quantityOrdered;
    }

}
