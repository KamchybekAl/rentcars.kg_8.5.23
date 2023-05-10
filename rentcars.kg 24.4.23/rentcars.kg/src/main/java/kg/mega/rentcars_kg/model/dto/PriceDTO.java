package kg.mega.rentcars_kg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.rentcars_kg.model.Car;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PriceDTO {
    private Long id;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Car car;
}
