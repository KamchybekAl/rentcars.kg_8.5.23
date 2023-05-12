package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.AddressMapper;
import kg.mega.rentcars_kg.mapper.CarMapper;
import kg.mega.rentcars_kg.mapper.OrderDetailMapper;
import kg.mega.rentcars_kg.model.Discount;
import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.model.Price;
import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;
import kg.mega.rentcars_kg.repository.OrderDetailRepo;
import kg.mega.rentcars_kg.service.*;
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
    private final CarService carService;
    private final CarMapper carMapper;
    private final MailSenderService mailSenderService;

    @Override
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO) {

        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        orderDetail.setCar(carService.findById(orderDetail.getCar().getId()));
        orderDetail.setOrderedDays(findReservedDays(orderDetail));
        orderDetail.setPriceBeforeDiscount(calculatePriceWithoutDiscount(orderDetail));
        orderDetail.setPriceWithDiscount(calculatePriceWithDiscount(orderDetail,calculatePriceWithoutDiscount(orderDetail)));
        orderDetail.setGetAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getGetAddress().getId())));
        orderDetail.setReturnAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getReturnAddress().getId())));
        OrderDetail save = orderDetailRepo.save(orderDetail);
        mailSenderService.sendOrderMessage(orderDetail);
        return orderDetailMapper.toDto(save);
    }
    private Double calculatePriceWithDiscount(OrderDetail orderDetail, Double priceWithoutDiscount){
        Discount discount = discountService.getActualDiscountByCarAndDaysCount(orderDetail.getCar(),findReservedDays(orderDetail));
//        Discount discount = discountService.getActualDiscountByCarAndDaysCount(carService.findById(orderDetail.getCar().getId()),findReservedDays(orderDetail));

        return priceWithoutDiscount-(priceWithoutDiscount*(discount.getDiscount()/100));
    }
    private Double calculatePriceWithoutDiscount(OrderDetail orderDetail){
        Price carPricePerDay = priceService.activePriceByCar(orderDetail.getCar());
//        Price carPricePerDay = priceService.activePriceByCar(carService.findById(orderDetail.getCar().getId()));
        Long daysCont = findReservedDays(orderDetail);
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
        return Math.abs(duration.toDays())+1;
    }

}