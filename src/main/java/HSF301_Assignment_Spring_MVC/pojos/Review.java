package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "REVIEWS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @EmbeddedId
    private ReviewKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carId")
    private Car car;

    @Column(name = "ReviewStar", nullable = false)
    @Max(5)
    @Min(1)
    private Integer reviewStar;

    @Column(name = "Comment", length = 500)
    private String comment;

    public Review(Customer cus, Car car) {
        this.car = car;
        this.customer = cus;
        this.id = new ReviewKey(cus.getCustomerID(), car.getCarId());
    }

    public Review(Customer cus, Car car, Integer reviewStar, String comment) {
        this.car = car;
        this.customer = cus;
        this.id = new ReviewKey(cus.getCustomerID(), car.getCarId());
        this.reviewStar = reviewStar;
        this.comment = comment;
    }
}
