package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.repositories.CarProducerRepository;
import HSF301_Assignment_Spring_MVC.services.ICarProducerService;
import HSF301_Assignment_Spring_MVC.pojos.CarProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarProducerService implements ICarProducerService {

    @Autowired
    private CarProducerRepository carProducerRepository;

    @Override
    public void save(CarProducer carProducer) {
        carProducerRepository.save(carProducer);
    }

    @Override
    public void update(CarProducer carProducer) {
        carProducerRepository.save(carProducer);
    }

    @Override
    public void delete(int id) {
        carProducerRepository.deleteById(id);
    }

    @Override
    public List<CarProducer> getAll() {
        return carProducerRepository.findAll();
    }

    @Override
    public CarProducer findByID(int id) {
        return carProducerRepository.getReferenceById(id);
    }
}
