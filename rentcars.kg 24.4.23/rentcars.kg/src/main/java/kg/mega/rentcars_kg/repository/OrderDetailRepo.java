package kg.mega.rentcars_kg.repository;

import kg.mega.rentcars_kg.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    @Query(value = "select * from tb_orderdetail where ?1 between date_time_from and date_time_to",nativeQuery = true)
    List<OrderDetail> findAllActive(LocalDateTime localDateTime);
}
