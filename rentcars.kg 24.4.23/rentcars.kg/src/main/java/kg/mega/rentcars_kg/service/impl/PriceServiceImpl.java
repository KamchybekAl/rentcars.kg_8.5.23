package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.PriceMapper;
import kg.mega.rentcars_kg.model.Car;
import kg.mega.rentcars_kg.model.Price;
import kg.mega.rentcars_kg.model.dto.PriceDTO;
import kg.mega.rentcars_kg.repository.PriceRepo;
import kg.mega.rentcars_kg.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@RequiredArgsConstructor
@Transactional
public class PriceServiceImpl implements PriceService {
    private final PriceRepo priceRepo;
    private final PriceMapper priceMapper;

    @Override
    public PriceDTO savePrice(PriceDTO priceDTO) {
        Price price = priceMapper.toEntity(priceDTO);
        price.setStartDate(LocalDate.now());
        price.setEndDate(price.getStartDate().plusYears(100));
        Price save = priceRepo.save(price);
        return priceMapper.toDto(save);
    }

    @Override
    public PriceDTO findById(Long id)  {
        return priceMapper.toDto(priceRepo.findById(id).get());
    }

    @Override
    public List<PriceDTO> findAll() {
        return priceMapper.toDTOList(priceRepo.findAll());
    }

    @Override
    public PriceDTO updatePrice(PriceDTO priceDTO) {
        Price updatePrice = priceRepo.findById(priceDTO.getId()).get();
        updatePrice.setPrice(priceDTO.getPrice());
        updatePrice.setStartDate(priceDTO.getStartDate());
        updatePrice.setEndDate(priceDTO.getEndDate());
        return priceMapper.toDto(updatePrice);
    }

    @Override
    public void deletePrice(Long id) {

    }

    @Override
    public Price activePriceByCar(Car car) {
        return priceRepo.findByCar(car).get();
    }
}
