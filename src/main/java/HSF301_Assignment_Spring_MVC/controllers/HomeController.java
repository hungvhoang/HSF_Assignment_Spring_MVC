package HSF301_Assignment_Spring_MVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/login"})
    public String loginView() {
        return "login";
    }
}
