package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.Car;

import java.util.List;

public interface ICarService {
    public void save(Car car);
    public void update(Car car);
    public void deleteById(int id);
    public List<Car> getAll();
    public Car findByID(int id);
    public void delete (Car car);
}
