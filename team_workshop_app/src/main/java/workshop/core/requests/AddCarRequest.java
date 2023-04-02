package workshop.core.requests;

public class AddCarRequest {
    private String brand;
    private String model;
    private String number;
    private String colorCode;

    public AddCarRequest(String brand, String model, String number, String colorCode) {
        this.brand = brand;
        this.model = model;
        this.number = number;
        this.colorCode = colorCode;
    }

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
}
