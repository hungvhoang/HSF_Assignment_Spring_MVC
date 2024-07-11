package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewKey implements Serializable {
    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "CarID")
    private int carId;
}
