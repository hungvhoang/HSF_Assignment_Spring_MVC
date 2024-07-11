package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.pojos.Review;
import HSF301_Assignment_Spring_MVC.services.ICarRentalService;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import HSF301_Assignment_Spring_MVC.services.ICustomerService;
import HSF301_Assignment_Spring_MVC.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ICarService iCarService;
    private ICustomerService iCustomerService;
    private ICarRentalService iCarRentalService;
    private IReviewService iReviewService;

    @Autowired
    public AdminController(ICarService iCarService, ICustomerService iCustomerService, ICarRentalService iCarRentalService,IReviewService iReviewService) {
        this.iCarService = iCarService;
        this.iCustomerService = iCustomerService;
        this.iCarRentalService = iCarRentalService;
        this.iReviewService = iReviewService;
    }

    @GetMapping("/carAdmin")
    public String getCars(Model model){
        List<Car> carList = iCarService.getAll();
        model.addAttribute("listCar",carList);
//        System.out.println(carList);
        return "carAdmin";
    }


    @GetMapping("/customer")
    public String getCustomer(Model model){
        List<Customer> customerList = iCustomerService.getByAccountRole("Customer");
        model.addAttribute("customer",customerList);
//        System.out.println(carList);
        return "customer";
    }

    @GetMapping("/carRental")
    public String getCarRental(Model model){
        List<CarRental> getCarRental = iCarRentalService.getAll();
        model.addAttribute("carRentalList",getCarRental);
        return "carRental";
    }

    @GetMapping("/review")
    public String getReview(Model model){
        List<Review> reviews = iReviewService.getAll();
        model.addAttribute("review",reviews);
        return "review";
    }
}
