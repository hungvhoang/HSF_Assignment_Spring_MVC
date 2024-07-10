package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import org.springframework.ui.Model;

public interface IAuthenticateService {
    public String Login(LoginRequest loginRequest, Model model);

}
