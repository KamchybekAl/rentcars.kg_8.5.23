package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.CarMapper;
import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.dto.CarDTO;
import kg.mega.rentcars_kg.repository.CarRepo;
import kg.mega.rentcars_kg.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;
    private final CarMapper carMapper;

    @Override
    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepo.findById(id).get();
    }

    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }

    @Override
    public Car updateCar(Car car) {
        Car updateCar = carRepo.findById(car.getId()).get();
        if (car.getCarModel() != null) {

            updateCar.setCarModel(car.getCarModel());
            updateCar.setCarCategory(car.getCarCategory());
            updateCar.setDescription(car.getDescription());
        }
        updateCar.setIsAvailable(car.getIsAvailable());

        return updateCar;
    }

    @Override
    public void deleteCar(Long id) {
        Car deleteCar = carRepo.findById(id).get();
        carRepo.delete(deleteCar);
    }

    @Override
    public List<Car> findAllAvailableCar(Boolean available) {
        return carRepo.findByIsAvailableIsTrue(available);
    }

    @Override
    public List<CarDTO> getCarCategory(String CarCategory) {
        return carMapper.toDTOList(carRepo.getCarCategory(CarCategory));
    }


}
