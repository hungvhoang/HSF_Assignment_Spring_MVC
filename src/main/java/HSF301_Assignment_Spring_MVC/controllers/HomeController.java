package HSF301_Assignment_Spring_MVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/login"})
    public String loginView(Model model) {
    	//Test passing props
    	model.addAttribute("message", "toiyeuAbigail");
        return "login";
    }
    
}
