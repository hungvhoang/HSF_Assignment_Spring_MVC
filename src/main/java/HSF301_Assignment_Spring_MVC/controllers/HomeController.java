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

    @GetMapping({"/home"})
    public String homeView(Model model){
        return "index";
    }

    @GetMapping({"/car"})
    public String carView(Model model){
        return "car";
    }

    @GetMapping({"/carDetail"})
    public String carDetailView(Model model){
        return "car-single";
    }

    @GetMapping({"/pricing"})
    public String pricingView(Model model){
        return "pricing";
    }

    @GetMapping({"/blog"}) //Lam` canh?
    public String blogView(Model model){
        return "blog";
    }

    
}
