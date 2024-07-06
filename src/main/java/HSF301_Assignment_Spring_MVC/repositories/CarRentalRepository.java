package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.CarRentalKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRentalRepository extends JpaRepository<CarRental, CarRentalKey> {

}
