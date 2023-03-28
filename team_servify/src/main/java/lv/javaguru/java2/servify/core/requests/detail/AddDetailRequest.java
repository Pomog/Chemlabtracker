package lv.javaguru.java2.servify.core.requests.detail;

public class AddDetailRequest {

    private String type;

    private String location;

    private String side;

    public AddDetailRequest(String type, String location, String side) {
        this.type = type;
        this.location = location;
        this.side = side;
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
}
