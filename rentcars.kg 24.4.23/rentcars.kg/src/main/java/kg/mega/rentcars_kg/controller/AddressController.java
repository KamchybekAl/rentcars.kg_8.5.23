package kg.mega.rentcars_kg.controller;

import kg.mega.rentcars_kg.model.dto.AddressDTO;
import kg.mega.rentcars_kg.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/save")
    public AddressDTO saveAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.saveAddress(addressDTO);
    }

    @GetMapping("/findById")
    public AddressDTO findById(@RequestParam Long id) {
        return addressService.findById(id);
    }

    @GetMapping("/findAll")
    public List<AddressDTO> findAll() {
        return addressService.findAll();
    }

    @PutMapping("/update")
    public AddressDTO updateAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.updateAddress(addressDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        addressService.deleteAddress(id);
    }

}
