package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CarRentalKey implements Serializable{

    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "CarID")
    private int carId;
}
