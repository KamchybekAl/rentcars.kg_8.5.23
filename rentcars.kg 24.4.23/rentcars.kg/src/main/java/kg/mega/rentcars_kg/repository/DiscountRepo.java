package kg.mega.rentcars_kg.repository;

import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {
    Optional<Discount> findByCar(Car car);
    @Query(value = "select * from tb_discount where car_id = ?1 and days_count_for_discount <= ?2  " +
            "and end_date > now() " +
            "order by days_count_for_discount desc limit 1", nativeQuery = true)
    Discount getActualDiscount(Long carId, Long daysCount);
}
