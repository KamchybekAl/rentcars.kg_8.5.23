package kg.mega.rentcars_kg.repository;

import kg.mega.rentcars_kg.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    @Query(value = "select * from tb_car where car_category=:CarCategory", nativeQuery = true)
    List<Car> getCarCategory(String CarCategory);
    @Query(value = "select * from tb_car where is_available=true", nativeQuery = true)
    List<Car>findByIsAvailableIsTrue(Boolean available);
}