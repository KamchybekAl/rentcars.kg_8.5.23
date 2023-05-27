package kg.mega.rentcars_kg.controller;

import kg.mega.rentcars_kg.model.dto.PriceDTO;
import kg.mega.rentcars_kg.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/save")
    public PriceDTO savePrice(@RequestBody PriceDTO priceDTO) {
        return priceService.savePrice(priceDTO);
    }

    @GetMapping("/findById")
    public PriceDTO findById(@RequestParam Long id) {
        return priceService.findById(id);
    }

    @GetMapping("/findAll")
    public List<PriceDTO> findAll() {
        return priceService.findAll();
    }

    @PutMapping("/update")
    public PriceDTO updatePrice(@RequestBody PriceDTO priceDTO) {
        return priceService.updatePrice(priceDTO);
    }


}
