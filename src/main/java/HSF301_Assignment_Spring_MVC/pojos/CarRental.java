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
import lombok.*;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date pickupDate;

    @Column(name = "ReturnDate")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    @Column(name = "Status", nullable = false)
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
}
