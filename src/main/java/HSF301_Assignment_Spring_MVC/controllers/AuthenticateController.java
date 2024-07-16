package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.repositories.CustomerRepository;
import HSF301_Assignment_Spring_MVC.services.Implements.AuthenticateService;
import HSF301_Assignment_Spring_MVC.services.Implements.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    private CustomerRepository customerRepository;
    @Autowired
    public AuthenticateController(AuthenticateService authenticateService, CustomerRepository customerRepository) {
        this.authenticateService = authenticateService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/oauth2/callback/google")
    public String googleLoginCallback(HttpServletRequest request, OAuth2User oAuth2User) {

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        Customer customer = customerRepository.findByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        session.setAttribute("user",customer);
        return "redirect:/home";
    }
    @PostMapping("/login")
    public String loginStudent(@ModelAttribute LoginRequest loginRequest, Model model, HttpServletRequest request) {
        Customer customer = authenticateService.login(loginRequest,model);
        if(customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user",customer);
            if(customer.getAccount().getRole().equalsIgnoreCase("Admin")) return "redirect:/admin/carAdmin";
            else return "redirect:/car";
        }
        model.addAttribute("notification","Username or password is not valid !");
        return "login";
    }

}
