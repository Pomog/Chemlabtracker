package lv.javaguru.java2.servify.domain;

import lv.javaguru.java2.servify.detail_builder.DetailBuilder;

import java.math.BigDecimal;
import java.util.Objects;

public class Detail {

    private Long id;
    private String type;
    // Bonnet – капот.
    // Boot  – багажник.
    // Bumper  – бампер.
    // Roof - крыша.
    // Door – дверь.
    // Wing - крыло автомобиля
    // Wing mirror  – боковое зеркало.
    private String location;
    private String side;
    private BigDecimal price;

    public Detail(DetailBuilder detailBuilder) {
        this.type = detailBuilder.getType();
        this.location = detailBuilder.getLocation();
        this.side = detailBuilder.getSide();
        this.price = detailBuilder.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getLocation() {
        return location;
    }

    public String getSide() {
        return side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", side='" + side + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detail detail)) return false;
        return Objects.equals(type, detail.type) && Objects.equals(getLocation(),
                detail.getLocation()) && Objects.equals(getSide(), detail.getSide());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, getLocation(), getSide());
    }

}
