package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.repositories.CarRepository;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void update(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findByID(int id) {
        return carRepository.getReferenceById(id);
    }
}
