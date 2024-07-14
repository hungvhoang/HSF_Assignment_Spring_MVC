package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.Implements.AuthenticateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
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
