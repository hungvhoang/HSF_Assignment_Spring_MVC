package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.repositories.CustomerRepository;
import HSF301_Assignment_Spring_MVC.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByID(int id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public Customer login(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}
