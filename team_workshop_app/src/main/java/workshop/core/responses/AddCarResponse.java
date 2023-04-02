package workshop.core.responses;

import workshop.Car;

import java.util.List;

public class AddCarResponse extends CoreResponse {

    private Car newCar;
    public AddCarResponse (List<CoreError> errors) {
        super(errors);
    }

    public AddCarResponse(Car newCar) {
        this.newCar = newCar;
    }

    public Car getNewCar() {
        return newCar;
    }
}
