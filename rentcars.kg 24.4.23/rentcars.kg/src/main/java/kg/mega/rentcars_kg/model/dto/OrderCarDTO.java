package kg.mega.rentcars_kg.model.dto;

import kg.mega.rentcars_kg.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderCarDTO {
    private List<LocalDate> reservedDates;
    private Car car;


}
