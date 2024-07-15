package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "CAR_RENTAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRental {
    @EmbeddedId
    private CarRentalKey id;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "CarID")
    private Car car;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "PickupDate", nullable = false)
    @NotNull(message = "Pickup date must be specified")
    @Temporal(TemporalType.DATE)
    private Date pickupDate;

    @Column(name = "ReturnDate")
    @NotNull(message = "Return date must be specified")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "RentPrice", nullable = false)
    @NotNull(message = "Rent price must be specified")
    @Positive(message = "Rent price must be a positive value")
    private double rentPrice;

    @Column(name = "Status", nullable = false)
    @NotBlank(message = "Status must be specified")
    @Size(max = 50, message = "Status should not exceed 50 characters")
    private String status;

    public CarRental(Customer cus, Car car, Date pickupDate, Date returnDate, double rentPrice, String status) {
        this.car = car;
        this.customer = cus;
        this.id = new CarRentalKey(cus.getCustomerID(), car.getCarId());
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.rentPrice = rentPrice;
        this.status = status;
    }

    public Date getCurrentDate(){
        return new Date();
    }

    @Override
    public String toString() {
        return "CarRental{" +
                "id=" + id +
                ", car=" + car +
                ", customer=" + customer +
                ", pickupDate=" + pickupDate +
                ", returnDate=" + returnDate +
                ", rentPrice=" + rentPrice +
                ", status='" + status + '\'' +
                '}';
    }

    public String getReview() {
        if(this.car != null) {
            if(!this.car.getCarReviewList().isEmpty()) {
                List<Review> listReview = car.getCarReviewList().stream().toList();
                AtomicReference<String> star = new AtomicReference<String>("");
                listReview.forEach(row -> {
                    if(row.getCustomer().getCustomerID() == this.customer.getCustomerID()) {
                        star.set(row.getComment());
                    }
                });
                return star.get();
            }
            return "";
        }
        return "";
    }

    public Integer getStars() {
        if(this.car != null) {
            if(!this.car.getCarReviewList().isEmpty()) {
                List<Review> listReview = car.getCarReviewList().stream().toList();
                AtomicInteger star = new AtomicInteger(0);
                listReview.forEach(row -> {
                    if(row.getCustomer().getCustomerID() == this.customer.getCustomerID()) {
                        star.set(row.getReviewStar());
                    }
                });
                return star.get();
            }
            return 0;
        }
        return 0;
    }
}
