package workshop.core.database;

import workshop.Car;
import workshop.core.database.Database;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImpl implements Database {
    private Long nextId = 1L;
    private List<Car> cars = new ArrayList<>();
    @Override
    public void save(Car car) {
        car.setId(nextId);
        nextId++;
        cars.add(car);
    }

    @Override
    public boolean deleteById(Long id) {
        for(Car car:cars){
            if(car.getId().equals(id)){
                cars.remove(car);
            }break;
        }
        return false;
    }

    @Override
    public List<Car> getAllCars() {
        return cars;
    }
}
