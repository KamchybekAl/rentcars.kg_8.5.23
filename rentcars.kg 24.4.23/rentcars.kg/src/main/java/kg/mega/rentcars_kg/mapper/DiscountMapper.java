package kg.mega.rentcars_kg.mapper;

import kg.mega.rentcars_kg.model.Discount;
import kg.mega.rentcars_kg.model.dto.DiscountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DiscountMapper {
    DiscountDTO toDto(Discount discount);

    Discount toEntity(DiscountDTO discountDTO);

    List<DiscountDTO> toDTOList(List<Discount> discountList);

    List<Discount> toEntityList(List<DiscountDTO> discountDTOList);

}
