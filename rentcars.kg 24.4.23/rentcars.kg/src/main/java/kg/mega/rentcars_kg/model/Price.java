package kg.mega.rentcars_kg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_price")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;
}
