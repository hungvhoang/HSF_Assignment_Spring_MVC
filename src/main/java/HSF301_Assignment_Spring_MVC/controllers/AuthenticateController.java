package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.Implements.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String loginStudent(@ModelAttribute LoginRequest loginRequest, Model model) {
        model.addAttribute("loginRequest", new LoginRequest());

            return authenticateService.Login(loginRequest, model);

//        System.out.println("ADSDADAD: "+ loginRequest.getUsername() + " " + loginRequest.getPassword());
//        return "login";
    }

}
