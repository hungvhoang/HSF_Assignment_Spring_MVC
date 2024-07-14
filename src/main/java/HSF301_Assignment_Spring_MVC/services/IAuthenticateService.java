package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import org.springframework.ui.Model;

public interface IAuthenticateService {
    public Customer login(LoginRequest loginRequest, Model model);

}
