package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.Customer;

import java.util.List;

public interface ICustomerService {
    public void save(Customer customer);
    public void update(Customer customer);
    public void delete(int id);
    public List<Customer> getAll();
    public Customer findByID(int id);
    public Customer login(String email, String password);
}
