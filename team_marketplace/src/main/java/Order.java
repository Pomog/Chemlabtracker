import java.math.BigDecimal;
import java.util.Objects;

class Order {
    private int orderId;
    private int userId;
    private int productId;
    private int quantity;
    private String status;
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Order(int orderId, int userId, int productId, int quantity, String status, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return orderId == order.orderId && userId == order.userId && productId == order.productId && quantity == order.quantity && Objects.equals(status, order.status) && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, productId, quantity, status, totalPrice);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
