package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.request.CarRentalRequest;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.ICarRentalService;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import HSF301_Assignment_Spring_MVC.services.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ICarService iCarService;
    private final ICarRentalService iCarRentalService;
    private final ICustomerService iCustomerService;

    @Autowired
    public HomeController(ICarService iCarService, ICarRentalService iCarRentalService,ICustomerService iCustomerService) {
        this.iCarService = iCarService;
        this.iCustomerService = iCustomerService;
        this.iCarRentalService = iCarRentalService;
    }

//    @GetMapping()
//    public String defaultRoot() {
//        return "redirect:/index";
//    }

//    @GetMapping("/index")
//    public String defaultScreen(Model model){
//        List<Car> carList = iCarService.getAll();
//        model.addAttribute("cars",carList);
//        return "index";
//    }

    @GetMapping({"/login"})
    public String loginView(Model model) {
    	//Test passing props
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/carRented")
    public String showCarRented(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        Customer customer =(Customer) session.getAttribute("user");
        List<CarRental> ds = customer.getCarRentalList().stream().toList();
        model.addAttribute("carRented",ds);
        return "customerRental";
    }

//    @GetMapping({"/about"})
//    public String aboutView(Model model){
////        model.addAttribute()
//        return "about";
//    }

//    @GetMapping({"/home"})
//    public String homeView(Model model){
//        return "index";
//    }

    @GetMapping({"/car"})
    public String carView(Model model){
        List<Car> carList = iCarService.getAll();
        model.addAttribute("cars",carList);
        return "car";
    }

    @GetMapping({"/carDetail/{id}"})
    public String carDetailView(Model model, @PathVariable int id){
        Car car = iCarService.findByID(id);
        model.addAttribute("car",car);
        return "car-single";
    }

//    @GetMapping({"/carDetail"})
//    public String carDetaView(Model model){
////        Car car = iCarService.findByID(id);
////        model.addAttribute("car",car);
//        return "car-single";
//    }

//    @GetMapping({"/pricing"})
//    public String pricingView(Model model){
//        return "pricing";
//    }
//
//    @GetMapping({"/blog"}) //Lam` canh?
//    public String blogView(Model model){
//        return "blog";
//    }

    @PostMapping({"/customer/rent-car"})
    public String userRentCar(Model model, @ModelAttribute CarRentalRequest crr){
        // Get list for render

//        CarRental carRental = iCarRentalService.update(cr);
        CarRental carRental = null;


//        if(carRental == null){
//            System.out.println("NULL CAR-RENTAL");
            model.addAttribute("err", "CarRental Not Found");
            List<Car> carList = iCarService.getAll();
            model.addAttribute("cars",carList);
//        }else{
//            model.addAttribute("carRental", carRental);
//        }
        return "car";
    }
    
}
