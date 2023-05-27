package kg.mega.rentcars_kg.mapper;

import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    OrderDetailDTO toDto(OrderDetail orderDetail);
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
    List<OrderDetailDTO> toDTOList (List<OrderDetail>orderDetailList);
//    List<OrderDetail> toEntity(List<OrderDetailDTO>orderDetailDTOList);
}
