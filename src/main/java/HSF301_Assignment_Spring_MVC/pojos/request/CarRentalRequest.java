package HSF301_Assignment_Spring_MVC.pojos.request;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.CarRentalKey;
import HSF301_Assignment_Spring_MVC.pojos.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class CarRentalRequest {
    private int carId;
    private int customerId;
    private Car car;
    private Customer customer;
    private int carModelYear;
    private Date pickupDate;
    private Date returnDate;
    private double rentPrice;
    private String status;

    public CarRental toCarRental() {
        return new CarRental(
                new CarRentalKey(car.getCarId(),customer.getCustomerID()),
                car,
                customer,
                pickupDate,
                returnDate,
                rentPrice,
                status
        );
    }

}
