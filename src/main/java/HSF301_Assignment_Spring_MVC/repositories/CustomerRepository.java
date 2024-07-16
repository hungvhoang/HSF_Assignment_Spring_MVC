package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmailAndPassword(String email, String password);

    public Customer findByEmail(String email);
    public List<Customer> getByAccountRole(String role);
}
