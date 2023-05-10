package kg.mega.rentcars_kg.controller;

import kg.mega.rentcars_kg.model.dto.OrderDetailDTO;
import kg.mega.rentcars_kg.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderdetail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping("/save")
    public OrderDetailDTO saveOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO){
        return orderDetailService.saveOrderDetail(orderDetailDTO);
    }
    @GetMapping("/findById")
    public OrderDetailDTO findById (@RequestParam Long id){
        return orderDetailService.findById(id);
    }
    @GetMapping("/findAll")
    public List<OrderDetailDTO> findAll(){
        return orderDetailService.findAll();
    }
    @PostMapping("/update")
    public OrderDetailDTO updateOrderDetail (@RequestBody OrderDetailDTO orderDetailDTO){
        return orderDetailService.updateOrderDetail(orderDetailDTO);
    }
}
