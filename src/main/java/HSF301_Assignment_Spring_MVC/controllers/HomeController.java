package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.*;
import HSF301_Assignment_Spring_MVC.pojos.request.CarRentalRequest;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.ICarRentalService;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import HSF301_Assignment_Spring_MVC.services.ICustomerService;
import HSF301_Assignment_Spring_MVC.services.IReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ICarService iCarService;
    private final ICarRentalService iCarRentalService;
    private final ICustomerService iCustomerService;
    private final IReviewService iReviewService;

    @Autowired
    public HomeController(ICarService iCarService, ICarRentalService iCarRentalService, ICustomerService iCustomerService,IReviewService iReviewService) {
        this.iCarService = iCarService;
        this.iCustomerService = iCustomerService;
        this.iCarRentalService = iCarRentalService;
        this.iReviewService = iReviewService;
    }

    @GetMapping()
    public String defaultRoot() {
        return "redirect:/login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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
    public String showCarRented(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Customer customer =(Customer) session.getAttribute("user");
        if(customer != null){
            List<CarRental> ds = iCarRentalService.getAll().stream()
                    .filter(carRental ->
                        carRental.getCustomer().getCustomerID() == customer.getCustomerID()
                    ).toList();
            model.addAttribute("carRented",ds);
            System.out.println("CUSTOMER");
        }else{
            System.out.println("CHUA CO CUSTOMER");
        }
        return "customerRental";
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
    public String carView(Model model) {
        List<Car> carList = iCarService.getAll();
        model.addAttribute("cars", carList);
        model.addAttribute("carRental", new CarRental());
        return "car";
    }

    @GetMapping({"/carDetail/{id}"})
    public String carDetailView(Model model, @PathVariable int id) {
        Car car = iCarService.findByID(id);
        model.addAttribute("car", car);
        return "car-single";
    }

//    @GetMapping({"/carDetail"})
//    public String carDetaView(Model model){
//        Car car = iCarService.findByID(id);
//        model.addAttribute("car",car);
//        return "car-single";
//    }

    @GetMapping("/services")
    private String serviceView(){
        return "services";
    }
    @GetMapping({"/pricing"})
    public String pricingView(Model model){
        return "pricing";
    }

    @GetMapping({"/blog"}) //Lam` canh?
    public String blogView(Model model){
        return "blog";
    }

    @GetMapping("/car/book/{carId}")
    public String selectedCar(Model model,
                              @PathVariable int carId
            , HttpServletRequest request) {
        Car car = iCarService.findByID(carId);
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        model.addAttribute("car", car);
        model.addAttribute("customer", customer);
        CarRental carRental = new CarRental();
        carRental.setCar(car);
        carRental.setCustomer(customer);
        model.addAttribute("carRental", carRental);
        return "customerBookCar";
    }

    @PostMapping({"/customer/rent-car"})
    public String userRentCar(@ModelAttribute CarRentalRequest carRental, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("user");
        if (cus == null) {
            redirectAttributes.addFlashAttribute("err", "Please sign in to continue");
        } else {
            CarRental entity = carRental.toCarRental();
            double price = (entity.getReturnDate().getTime() - entity.getPickupDate().getTime()) * entity.getCar().getRentPrice();
            if (price == 0) {
                price = entity.getCar().getRentPrice();
            }
            entity.setStatus("ok");
            entity.setRentPrice(price);
            iCarRentalService.update(entity);
            redirectAttributes.addFlashAttribute("err", "Add CarRental Successfully");
        }

//        System.out.println("::: "+ carRental.toString())

        return "redirect:/car";
    }


    @GetMapping("/customer/review")
    public String loadDataForReview(Model model,
                                    @RequestParam int id, HttpServletRequest request) {
        Review review = new Review();
        HttpSession session = request.getSession();
        Car car = iCarService.findByID(id);
        Customer customer =(Customer) session.getAttribute("user");
        review.setCar(car);
        review.setCustomer(customer);
        model.addAttribute("review",review);
        return "customerWriteReview";
    }

    @PostMapping("/customer/review")
    public String createReview(@ModelAttribute Review review){
        review.setId(new ReviewKey(review.getCustomer().getCustomerID(),review.getCar().getCarId()));
        iReviewService.save(review);
        return "redirect:/carRented";
    }

}
