package workshop;

import java.util.Objects;

public class Car {
    private Long id;
    private String brand;
    private String model;
    private String number;
    private String colorCode;

    public Long getId() { return id; }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public String getColorCode() {
        return colorCode;
    }

    public Car(String brand, String model, String number, String colorCode) {
        this.brand = brand;
        this.model = model;
        this.number = number;
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "workshop.Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return getId().equals(car.getId()) && getBrand().equals(car.getBrand()) && getModel().equals(car.getModel()) && getNumber().equals(car.getNumber()) && getColorCode().equals(car.getColorCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getNumber(), getColorCode());
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
