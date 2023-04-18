package shop.core.domain.cart;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Cart {

    private Long id;
    private final Long userId;
    private CartStatus cartStatus;
    private LocalDate lastActionDate;

    public Cart(long userId) {
        this.userId = userId;
        this.cartStatus = CartStatus.OPEN;
        this.lastActionDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", user_id=" + userId +
                ", cartStatus=" + cartStatus +
                ", lastActionDate=" + lastActionDate;
    }

}
