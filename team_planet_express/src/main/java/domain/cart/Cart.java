package domain.cart;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(lastActionDate, cart.lastActionDate) && cartStatus == cart.cartStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastActionDate, cartStatus);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", user_id=" + userId +
                ", cartStatus=" + cartStatus +
                ", lastActionDate=" + lastActionDate;
    }

}
