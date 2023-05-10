package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.AddressMapper;
import kg.mega.rentcars_kg.mapper.DiscountMapper;
import kg.mega.rentcars_kg.mapper.OrderDetailMapper;
import kg.mega.rentcars_kg.model.Discount;
import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.model.Price;
import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;
import kg.mega.rentcars_kg.repository.AddressRepo;
import kg.mega.rentcars_kg.repository.DiscountRepo;
import kg.mega.rentcars_kg.repository.OrderDetailRepo;
import kg.mega.rentcars_kg.service.AddressService;
import kg.mega.rentcars_kg.service.DiscountService;
import kg.mega.rentcars_kg.service.OrderDetailService;
import kg.mega.rentcars_kg.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepo orderDetailRepo;
    private final OrderDetailMapper orderDetailMapper;
    private final PriceService priceService;
    private final DiscountService discountService;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO) {

        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        orderDetail.setOrderedDays(findReservedDays(orderDetail));
        orderDetail.setPriceBeforeDiscount(calculatePriceWithoutDiscount(orderDetail));
        orderDetail.setPriceWithDiscount(calculatePriceWithDiscount(orderDetail,calculatePriceWithoutDiscount(orderDetail)));
        orderDetail.setGetAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getGetAddress().getId())));
        orderDetail.setReturnAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getReturnAddress().getId())));
        OrderDetail save = orderDetailRepo.save(orderDetail);
        return orderDetailMapper.toDto(save);
    }

    private Double calculatePriceWithDiscount(OrderDetail orderDetail, Double priceWithoutDiscount){
        Discount discount = discountService.getActualDiscountByCarAndDaysCount(orderDetail.getCar(),findReservedDays(orderDetail));
        return priceWithoutDiscount-(priceWithoutDiscount*(discount.getDiscount()/100));
    }
    private Double calculatePriceWithoutDiscount(OrderDetail orderDetail){
        Price carPricePerDay = priceService.activePriceByCar(orderDetail.getCar());
        Duration duration = Duration.between(orderDetail.getDateTimeFrom(),orderDetail.getDateTimeTo());
        Long daysCont = Math.abs(duration.toDays());
        return carPricePerDay.getPrice()*daysCont;
    }



    @Override
    public OrderDetailDTO findById(Long id) {
        return orderDetailMapper.toDto(orderDetailRepo.findById(id).get());
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        return orderDetailMapper.toDTOList(orderDetailRepo.findAll());
    }
    @Override
    public OrderDetailDTO updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail updateOrderDetail = orderDetailRepo.findById(orderDetailDTO.getId()).get();
//        венуть позже с OrderDetailServiceImpl 8.5.23 текст файл
        return orderDetailMapper.toDto(updateOrderDetail);
    }

    private Long findReservedDays(OrderDetail orderDetail) {
        Duration duration = Duration.between(orderDetail.getDateTimeFrom(), orderDetail.getDateTimeTo());
        return duration.toDays();
    }

}