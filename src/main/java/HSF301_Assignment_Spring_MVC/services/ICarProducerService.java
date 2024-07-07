package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.CarProducer;

import java.util.List;

public interface ICarProducerService {

    public void save(CarProducer carProducer);

    public void update(CarProducer carProducer);

    public void delete(int id);

    public List<CarProducer> getAll();

    public CarProducer findByID(int id);

}
