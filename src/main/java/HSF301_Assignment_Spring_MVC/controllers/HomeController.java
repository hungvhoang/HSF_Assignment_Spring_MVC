package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"/","/login"})
    public String loginView(Model model) {
    	//Test passing props
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping({"/about"})
    public String aboutView(Model model){
//        model.addAttribute()
        return "about";
    }
    
}
