package kg.mega.rentcars_kg.model.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String City;
    private String street;
    private String buildingNumber;

}
