package kg.mega.rentcars_kg.service;

import kg.mega.rentcars_kg.model.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO saveAddress (AddressDTO addressDTO);
    AddressDTO findById(Long id);
    List<AddressDTO>findAll();
    AddressDTO updateAddress (AddressDTO addressDTO);
    void deleteAddress(Long id);




}
