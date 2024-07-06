package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewKey implements Serializable {
    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "CarID")
    private int carId;
}
