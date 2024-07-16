package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.pojos.CarProducer;
import HSF301_Assignment_Spring_MVC.pojos.Category;
import HSF301_Assignment_Spring_MVC.repositories.CarProducerRepository;
import HSF301_Assignment_Spring_MVC.repositories.CarRepository;
import HSF301_Assignment_Spring_MVC.repositories.CategoryRepository;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarService implements ICarService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CarProducerRepository carProducerRepository;

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
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(Car car){
        Category category = car.getCategoryID();
        Set<Car> carsCate = category.getCarList();
        carsCate.remove(car);
        categoryRepository.save(category);

        CarProducer carProducer = car.getProducer();
        Set<Car> carsPro = carProducer.getCar();
        carsPro.remove(car);
        carProducerRepository.save(carProducer);

        carRepository.delete(car);
    }

    @Override
    public List<Car> findByName(String name, Integer page) {
        return carRepository.findCarsByName(name);
    }

    @Override
    public List<Car> getAllCarsByPageFilterByName(String carName, Integer page){
        return carRepository.getAllCarsByPageFilterByName(carName, page);
    }

    @Override
    public int getTotalPage() {
        int totalCars = carRepository.getCarsQuantity();
        return (int) Math.ceil((double) totalCars / 6.0);
    }

    @Override
    public Car findByID(int id) {
        return carRepository.getReferenceById(id);
    }
}
