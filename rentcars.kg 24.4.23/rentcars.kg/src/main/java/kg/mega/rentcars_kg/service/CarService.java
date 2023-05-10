package kg.mega.rentcars_kg.service;

import kg.mega.rentcars_kg.model.Car;

import java.util.List;

public interface CarService {
    Car saveCar (Car car);
    Car findById (Long id);
    List<Car> findAll();
    Car updateCar (Car car);
    void deleteCar(Long id);

}
