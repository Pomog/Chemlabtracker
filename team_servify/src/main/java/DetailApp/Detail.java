package DetailApp;

import java.util.Objects;

public class Detail {
    private String type;
    // Bonnet – капот.
    // Door – дверь.
    // Boot  – багажник.
    // Bumper  – бампер.
    // Roof - крыша.
    // Wing - крыло автомобиля
    // Wing mirror  – боковое зеркало.
    // Windscreen  – лобовое стекло.
    // Headlight  – фара.
    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // front , rear
    private String side;

    public String getLocation() {
        return location;
    }

    public String getSide() {
        return side;
    }

    public double getPrice() {
        return price;
    }

    // left , right
    private double price;

    public Detail(String type, String location, String side) {
        this.type = type;
        this.location = location;
        this.side = side;
    }

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
