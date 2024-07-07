package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.CarRentalKey;
import HSF301_Assignment_Spring_MVC.repositories.CarRentalRepository;
import HSF301_Assignment_Spring_MVC.services.ICarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalService implements ICarRentalService {

    @Autowired
    private CarRentalRepository carRentalRepository;

    @Override
    public void save(CarRental carRental) {
        carRentalRepository.save(carRental);
    }

    @Override
    public void update(CarRental carRental) {
        carRentalRepository.save(carRental);
    }

    @Override
    public void delete(CarRentalKey id) {
        carRentalRepository.deleteById(id);
    }

    @Override
    public List<CarRental> getAll() {
        return carRentalRepository.findAll();
    }

    @Override
    public CarRental findByID(CarRentalKey id) {
        return carRentalRepository.getReferenceById(id);
    }
}
