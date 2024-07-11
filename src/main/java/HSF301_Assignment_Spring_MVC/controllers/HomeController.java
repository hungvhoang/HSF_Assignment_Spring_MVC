package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private ICarService iCarService;

    @Autowired
    public HomeController(ICarService iCarService) {
        this.iCarService = iCarService;
    }

    @GetMapping()
    public String defaultRoot() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String defaultScreen(Model model){
        List<Car> carList = iCarService.getAll();
        model.addAttribute("cars",carList);
        return "index";
    }

    @GetMapping({"/login"})
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
        List<Car> carList = iCarService.getAll();
        model.addAttribute("cars",carList);
        return "car";
    }

    @GetMapping({"/carDetail"})
    public String carDetailView(Model model){
        return "car-single";
    }

    @GetMapping({"/carRental"})
    public String carRentalView(Model model){
        return "carRental";
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
