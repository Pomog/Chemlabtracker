package workshop.core.database;

import workshop.Car;

import java.util.List;

public interface Database {
    void save(Car car);
    boolean deleteById(Long id);
    List<Car> getAllCars();
}
