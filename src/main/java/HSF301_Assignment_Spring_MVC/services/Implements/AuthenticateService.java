package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Account;
import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.repositories.AccountRepository;
import HSF301_Assignment_Spring_MVC.repositories.CustomerRepository;
import HSF301_Assignment_Spring_MVC.services.IAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthenticateService implements IAuthenticateService {

    private final AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public AuthenticateService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer login(LoginRequest loginRequest, Model model) {
        try {
            Customer customer = customerRepository.findByEmailAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            return customer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
