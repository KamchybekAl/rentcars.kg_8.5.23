package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.DiscountMapper;
import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.Discount;
import kg.mega.rentcars_kg.model.dto.DiscountDTO;
import kg.mega.rentcars_kg.repository.CarRepo;
import kg.mega.rentcars_kg.repository.DiscountRepo;
import kg.mega.rentcars_kg.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepo discountRepo;
    private final DiscountMapper discountMapper;
    private final CarRepo carRepo;

    @Override
    public DiscountDTO saveDiscount(DiscountDTO discountDTO) {
        Discount discount = discountMapper.toEntity(discountDTO);
        discount.setStartDate(LocalDate.now());
        discount.setEndDate(discount.getStartDate().plusYears(100));
        discount.setCar(carRepo.findById(discountDTO.getCar().getId()).get());
        Discount save = discountRepo.save(discount);
        return discountMapper.toDto(save);
    }

    @Override
    public DiscountDTO findById(Long id) {
        return discountMapper.toDto(discountRepo.findById(id).get());
    }

    @Override
    public List<DiscountDTO> findAll() {
        return discountMapper.toDTOList(discountRepo.findAll());
    }

    @Override
    public DiscountDTO updateDiscount(DiscountDTO discountDTO) {
        Discount updateDiscount = discountRepo.findById(discountDTO.getId()).get();
        updateDiscount.setDiscount(discountDTO.getDiscount());
        updateDiscount.setStartDate(discountDTO.getStartDate());
        updateDiscount.setEndDate(discountDTO.getEndDate());
        return discountMapper.toDto(updateDiscount);
    }

    @Override
    public Discount getActualDiscountByCarAndDaysCount(Car car, Long daysCount) {
        return discountRepo.getActualDiscount(car.getId(), daysCount);
    }
}
