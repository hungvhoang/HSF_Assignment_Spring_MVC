package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.CarRentalKey;

import java.util.List;

public interface ICarRentalService {
    public void save(CarRental carRental);
    public CarRental update(CarRental carRental);
    public void delete(CarRentalKey id);
    public List<CarRental> getAll();
    public CarRental findByID(CarRentalKey id);
}
