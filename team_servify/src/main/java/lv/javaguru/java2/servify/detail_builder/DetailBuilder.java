package lv.javaguru.java2.servify.detail_builder;

import lv.javaguru.java2.servify.domain.Detail;

import java.math.BigDecimal;

public class DetailBuilder {

    private Long id;
    private String type;
    private String location;
    private String side;
    private BigDecimal price;

    public DetailBuilder(String type) {
        this.type = type;
    }

    public DetailBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public DetailBuilder setSide(String side) {
        this.side = side;
        return this;
    }

    public DetailBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Detail build() {
        return new Detail(this);
    }

    public String getType() {
        return type;
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
}
