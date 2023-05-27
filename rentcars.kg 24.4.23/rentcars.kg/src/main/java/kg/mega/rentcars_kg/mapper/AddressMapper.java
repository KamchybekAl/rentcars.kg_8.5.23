package kg.mega.rentcars_kg.mapper;

import kg.mega.rentcars_kg.model.Address;
import kg.mega.rentcars_kg.model.dto.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressDTO toDto(Address address);

    Address toEntity(AddressDTO addressDTO);

    List<AddressDTO> toDTOList(List<Address> addressList);

//    List<Address> toEntityList(List<AddressDTO> addressDTOList);

}
