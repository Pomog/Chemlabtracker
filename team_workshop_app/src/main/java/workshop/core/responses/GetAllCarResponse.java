package workshop.core.responses;

import workshop.Car;

import java.util.List;

public class GetAllCarResponse {
    private List<Car> cars;

    public GetAllCarResponse(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
