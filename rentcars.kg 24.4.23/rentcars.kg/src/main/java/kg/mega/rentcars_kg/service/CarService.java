package kg.mega.rentcars_kg.service;

import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.dto.CarDTO;

import java.util.List;

public interface CarService {
    Car saveCar (Car car);
    Car findById (Long id);
    List<Car> findAll();
    Car updateCar (Car car);
    void deleteCar(Long id);
    List<Car>findAllAvailableCar(Boolean available);

    List<CarDTO> getCarCategory(String CarCategory);

}
