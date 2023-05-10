package kg.mega.rentcars_kg.mapper;

import kg.mega.rentcars_kg.model.Price;
import kg.mega.rentcars_kg.model.dto.PriceDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface PriceMapper {
    PriceDTO toDto(Price Price);
    Price toEntity(PriceDTO priceDTO);
    List<PriceDTO> toDTOList(List<Price>priceList);
    List<Price> toEntityList(List<PriceDTO>priceDTOList);

}
