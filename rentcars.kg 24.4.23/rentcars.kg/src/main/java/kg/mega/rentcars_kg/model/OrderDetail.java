package kg.mega.rentcars_kg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_orderdetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean babySeat;
    private Boolean withDriver;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private Double priceBeforeDiscount;
    private Double priceWithDiscount; // Total price
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeTo;
    private Long orderedDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "getAddress",referencedColumnName = "id")

    private Address getAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "returnAddress",referencedColumnName = "id")
    private Address returnAddress;
    @ManyToOne(fetch = FetchType.EAGER)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Car car;

}
