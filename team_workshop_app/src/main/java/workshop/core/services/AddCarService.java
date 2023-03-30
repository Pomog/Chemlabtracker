package workshop.core.services;

import workshop.AddCarValidator;
import workshop.CoreError;
import workshop.core.requests.AddCarRequest;
import workshop.core.responses.AddCarResponse;
import workshop.Car;
import workshop.core.database.Database;

import java.util.List;

public class AddCarService {
    private Database database;
    private AddCarValidator validator;

    public AddCarService(Database database, AddCarValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddCarResponse execute(AddCarRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCarResponse(errors);
        }
        Car car = new Car(request.getBrand(),request.getModel(),request.getNumber(),request.getColorCode());
        database.save(car);


        return new AddCarResponse(car);
    }
}
