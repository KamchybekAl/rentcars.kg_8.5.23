package kg.mega.rentcars_kg.controller;

import kg.mega.rentcars_kg.model.dto.DiscountDTO;
import kg.mega.rentcars_kg.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/save")
    public DiscountDTO saveDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountService.saveDiscount(discountDTO);
    }

    @GetMapping("/findById")
    public DiscountDTO findById(@RequestParam Long id) {
        return discountService.findById(id);
    }

    @GetMapping("/findAll")
    public List<DiscountDTO> findAll() {
        return discountService.findAll();
    }

    @PutMapping("/update")
    public DiscountDTO updateDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountService.updateDiscount(discountDTO);
    }


}
