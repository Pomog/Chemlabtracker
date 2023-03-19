import product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class Order {
    private int userId;
    List<Product> productList = new ArrayList<>();

    private int orderId;
    private int quantity = productList.size();
    private String status;
    private BigDecimal totalPrice;



    public BigDecimal getTotalPrice() {
        for (Product product: productList){
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}