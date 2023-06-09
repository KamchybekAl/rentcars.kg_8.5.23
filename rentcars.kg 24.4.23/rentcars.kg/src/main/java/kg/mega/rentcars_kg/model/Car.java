package kg.mega.rentcars_kg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.rentcars_kg.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String description;
    private Double engineCapacity;
    @Column(name = "is_available")
    private Boolean isAvailable;
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
