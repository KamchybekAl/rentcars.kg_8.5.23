package kg.mega.rentcars_kg.repository;

import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    Optional<Price> findByCar(Car car);
}
