package workshop.core.services;

import workshop.Car;
import workshop.core.requests.GetAllCarRequest;
import workshop.core.responses.GetAllCarResponse;
import workshop.core.database.Database;

import java.util.List;

public class GetAllCarService {
   private Database database;

    public GetAllCarService(Database database) {
        this.database = database;
    }

    public GetAllCarResponse execute(GetAllCarRequest request){
        List<Car> cars = database.getAllCars();

        return new  GetAllCarResponse(cars);
    }
}
