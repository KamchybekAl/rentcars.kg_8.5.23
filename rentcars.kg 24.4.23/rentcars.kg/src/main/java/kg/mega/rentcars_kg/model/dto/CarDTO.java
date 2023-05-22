package kg.mega.rentcars_kg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.rentcars_kg.model.enums.*;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class CarDTO {
    private Long id;
    private String photo;
    private String description;
    private Double engineCapacity;
    private Boolean isAvailable ;
    private Double fuelConsumption;
    @JsonFormat(pattern = "YYYY")
    private Date mnfYear;
    @Enumerated(value = EnumType.STRING)
    private CarModel carModel;
    @Enumerated(value = EnumType.STRING)
    private CarColor carColor;
    @Enumerated(value = EnumType.STRING)
    private EngineType engineType;
    @Enumerated(value = EnumType.STRING)
    private CarTransmission carTransmission;
    @Enumerated(value = EnumType.STRING)
    private CarCategory carCategory;
}
