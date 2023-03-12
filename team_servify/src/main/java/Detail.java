import java.math.BigDecimal;
import java.util.Objects;

public class Detail {
    private String type;
    // Bonnet – капот.
    // Boot  – багажник.
    // Bumper  – бампер.
    // Roof - крыша.
    // Door – дверь.
    // Wing - крыло автомобиля
    // Wing mirror  – боковое зеркало.

    private String location;
    private BigDecimal price;
    private String side;

    Detail(String type, String location, String side) {
        this.type = type;
        this.location = location;
        this.side = side;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setSide(String side) {
        this.side = side;
    }

    String getLocation() {
        return location;
    }

    String getSide() {
        return side;
    }


    @Override
    public String toString() {
        return "Detail{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", side='" + side + '\'' +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detail detail)) return false;
        return Objects.equals(type, detail.type) && Objects.equals(getLocation(), detail.getLocation()) && Objects.equals(price, detail.price) && Objects.equals(getSide(), detail.getSide());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, getLocation(), price, getSide());
    }
}
