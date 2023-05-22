package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.AddressMapper;
import kg.mega.rentcars_kg.mapper.OrderDetailMapper;
import kg.mega.rentcars_kg.model.Discount;
import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.model.Price;
import kg.mega.rentcars_kg.model.dto.OrderCarDTO;
import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;
import kg.mega.rentcars_kg.repository.OrderDetailRepo;
import kg.mega.rentcars_kg.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final MailSenderService mailSenderService;

    @Override
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO) {

        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        orderDetail.setCar(carService.findById(orderDetail.getCar().getId()));
        orderDetail.setOrderedDays(findReservedDays(orderDetail));
        orderDetail.setPriceBeforeDiscount(calculatePriceWithoutDiscount(orderDetail));
        orderDetail.setPriceWithDiscount(calculatePriceWithDiscount(orderDetail, calculatePriceWithoutDiscount(orderDetail)));
        orderDetail.setGetAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getGetAddress().getId())));
        orderDetail.setReturnAddress(addressMapper.toEntity(addressService.findById(orderDetailDTO.getReturnAddress().getId())));
        OrderDetail save = orderDetailRepo.save(orderDetail);
        mailSenderService.sendOrderMessage(orderDetail);
        return orderDetailMapper.toDto(save);
    }

    private Double calculatePriceWithDiscount(OrderDetail orderDetail, Double priceWithoutDiscount) {
        Discount discount = discountService.getActualDiscountByCarAndDaysCount(orderDetail.getCar(), findReservedDays(orderDetail));
        if (orderDetail.getOrderedDays() != null && orderDetail.getOrderedDays() > 2) { // отправляет: Итоговая сумма с учётом Вашей скидки: null  ??
            return priceWithoutDiscount - (priceWithoutDiscount * (discount.getDiscount() / 100));
        } else {
            return 0.0;
        }
    }

    private Double calculatePriceWithoutDiscount(OrderDetail orderDetail) {
        Price carPricePerDay = priceService.activePriceByCar(orderDetail.getCar());
        Long daysCont = findReservedDays(orderDetail);
        return carPricePerDay.getPrice() * daysCont;
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        return orderDetailMapper.toDto(orderDetailRepo.findById(id).orElse(null));
    }

    @Override
    public List<OrderDetailDTO> findAll() {

        return orderDetailMapper.toDTOList(orderDetailRepo.findAll());
    }

    @Override
    public OrderDetailDTO updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail updateOrderDetail = orderDetailRepo.findById(orderDetailDTO.getId()).orElse(null);
//        венуть позже с OrderDetailServiceImpl 8.5.23 текст файл
        return orderDetailMapper.toDto(updateOrderDetail);
    }

    @Override
    public List<OrderCarDTO> findAllActive() {
//        OrderCarDTO orderCarDTO = new OrderCarDTO();
        List<OrderCarDTO> toArryList = new ArrayList<>();
        for (OrderDetail or : orderDetailRepo.findAllActive(LocalDateTime.now())) {
            toArryList.add(new OrderCarDTO(or.getDateTimeFrom().toLocalDate().datesUntil(or.getDateTimeTo().toLocalDate()).collect(Collectors.toList()), or.getCar()));
        }
        return toArryList;
    }

    private Long findReservedDays(OrderDetail orderDetail) {
        Duration duration = Duration.between(orderDetail.getDateTimeFrom(), orderDetail.getDateTimeTo());
        return duration.toDays();
    }
}