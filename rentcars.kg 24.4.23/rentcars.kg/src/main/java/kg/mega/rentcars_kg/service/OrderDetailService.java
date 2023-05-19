package kg.mega.rentcars_kg.service;

import kg.mega.rentcars_kg.model.dto.OrderCarDTO;
import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDTO saveOrderDetail (OrderDetailDTO orderDetailDTO);
    OrderDetailDTO findById (Long id);
    List<OrderDetailDTO> findAll();
    OrderDetailDTO updateOrderDetail (OrderDetailDTO orderDetailDTO);
    List<OrderCarDTO> findAllActive();

}
