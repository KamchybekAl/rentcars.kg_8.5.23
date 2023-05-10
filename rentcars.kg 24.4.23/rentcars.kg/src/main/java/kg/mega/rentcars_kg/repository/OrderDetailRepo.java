package kg.mega.rentcars_kg.repository;

import kg.mega.rentcars_kg.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {

}
