package DetailApp;

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
    private double price;
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

    void setPrice(double price) {
        this.price = price;
    }

    // front , rear
    String getLocation() {
        return location;
    }

    String getSide() {
        return side;
    }

    double getPrice() {
        return price;
    }
    // left , right

    @Override
    public String toString() {
        return "Detail{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", side='" + side + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detail detail)) return false;
        return Double.compare(detail.price, price) == 0 && Objects.equals(type, detail.type) && Objects.equals(location, detail.location) && Objects.equals(side, detail.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, location, side, price);
    }

}
