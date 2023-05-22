package kg.mega.rentcars_kg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.rentcars_kg.model.Car;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountDTO {
    private Long id;
    private Double discount;
    private Long daysCountForDiscount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Car car;

}
