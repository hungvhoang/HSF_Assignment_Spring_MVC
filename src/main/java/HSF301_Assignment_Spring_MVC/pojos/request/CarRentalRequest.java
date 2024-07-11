package HSF301_Assignment_Spring_MVC.pojos.request;

import lombok.Data;

@Data
public class CarRentalRequest {
    private int carId;
    private int customerID;
    private int carModelYear;
    private String pickupDate;
    private String returnDate;
    private double rentPrice;
    private String statusString;

}
