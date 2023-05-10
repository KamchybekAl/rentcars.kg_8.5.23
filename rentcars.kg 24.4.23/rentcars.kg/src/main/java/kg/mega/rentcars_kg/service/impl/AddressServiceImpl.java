package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.mapper.AddressMapper;
import kg.mega.rentcars_kg.model.Address;
import kg.mega.rentcars_kg.model.dto.AddressDTO;
import kg.mega.rentcars_kg.repository.AddressRepo;
import kg.mega.rentcars_kg.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        Address save = addressRepo.save(address);
        return addressMapper.toDto(save);
    }
    @Override
    public AddressDTO findById(Long id){
        return addressMapper.toDto(addressRepo.findById(id).get());
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressMapper.toDTOList(addressRepo.findAll());
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address updateAddress = addressRepo.findById(addressDTO.getId()).get();
        updateAddress.setCity(addressDTO.getCity());
        updateAddress.setStreet(addressDTO.getStreet());
        updateAddress.setBuildingNumber(addressDTO.getBuildingNumber());
        return addressMapper.toDto(updateAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address deleteAddress = addressRepo.findById(id).get();
        addressRepo.delete(deleteAddress);
    }

}
