package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.dtos.CarPricingDTO;
import HSF301_Assignment_Spring_MVC.pojos.*;
import HSF301_Assignment_Spring_MVC.pojos.request.CarRentalRequest;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.services.ICarRentalService;
import HSF301_Assignment_Spring_MVC.services.ICarService;
import HSF301_Assignment_Spring_MVC.services.ICustomerService;
import HSF301_Assignment_Spring_MVC.services.IReviewService;
import HSF301_Assignment_Spring_MVC.services.email.MailService;
import HSF301_Assignment_Spring_MVC.services.vnpay.PaymentService;
import HSF301_Assignment_Spring_MVC.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private MailService mailService;

    private PaymentService paymentService;

    @Autowired
    public HomeController(
            ICarService iCarService,
            ICarRentalService iCarRentalService,
            PaymentService paymentService,
            MailService mailService,
            ICustomerService iCustomerService,
            IReviewService iReviewService,
            JavaMailSender emailSender) {
        this.iCarService = iCarService;
        this.iCustomerService = iCustomerService;
        this.iCarRentalService = iCarRentalService;
        this.mailService = mailService;
        this.paymentService = paymentService;
        this.iReviewService = iReviewService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("")
    public String defaultScreen(Model model) {
        List<Car> carList = iCarService.getAll();
        model.addAttribute("cars", carList);
        return "index";
    }

    @GetMapping({"/login"})
    public String loginView(Model model) {
        //Test passing props
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/carRented")
    public String showCarRented(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        if (customer != null) {
            List<CarRental> ds = iCarRentalService.getAll().stream()
                    .filter(carRental ->
                            carRental.getCustomer().getCustomerID() == customer.getCustomerID()
                    ).toList();
            model.addAttribute("carRented", ds);
            System.out.println("CUSTOMER");
        } else {
            System.out.println("CHUA CO CUSTOMER");
        }
        return "customerRental";
    }

    @GetMapping({"/about"})
    public String aboutView(Model model) {
//        model.addAttribute()
        return "about";
    }

    @GetMapping({"/home"})
    public String homeView(Model model) {
        return "index";
    }

    @GetMapping("/car/redirect")
    public String redirectToCar(@RequestParam(value = "carName", required = false) String carName,
                                @RequestParam(value = "page", required = false) Integer page,
                                RedirectAttributes redirectAttributes) {
        if (carName != null) {
            redirectAttributes.addAttribute("carName", carName);
        }
        if (page != null) {
            redirectAttributes.addAttribute("page", page);
        }
        return "redirect:/car";
    }

    @GetMapping({"/car"})
    public String carView(Model model, @RequestParam(value = "carName", required = false) String carName, @RequestParam(value = "page", required = false) Integer page) {

        final int itemPerPage = 6; // 6 item each page

        carName = (carName == null) ? "" : carName;
        page = (page == null) ? 1 : page;

        List<Car> carList = iCarService.getAllCarsByPageFilterByName(carName, page);
        int totalPage = iCarService.getTotalPage();
        model.addAttribute("cars", carList);
        model.addAttribute("carName", carName); // for displaying input
        model.addAttribute("totalPage", totalPage); //display totalPage
        model.addAttribute("currentPage", page); //display currentPage
        return "car";
    }

    @GetMapping({"/carDetail/{id}"})
    public String carDetailView(Model model, @PathVariable int id) {
        Car car = iCarService.findByID(id);
        model.addAttribute("car", car);
        return "car-single";
    }

    @GetMapping("/services")
    private String serviceView() {
        return "services";
    }

    @GetMapping({"/pricing"})
    public String pricingView(Model model) {
        List<CarPricingDTO> priceList = iCarService.getAll().stream()
                .map(CarPricingDTO::fromCar)
                .toList();
        model.addAttribute("priceList", priceList);
        return "pricing";
    }

    @GetMapping("logout")
    private String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        if (customer != null) {
            session.invalidate();
        }
        return "index";
    }


    @GetMapping({"/blog"}) //Lam` canh?
    public String blogView(Model model) {
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

    @GetMapping("/successfully/{id}")
    public String showSuccessfully(HttpServletRequest request, @RequestParam int id) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        CarRental carRental = iCarRentalService.findByID(new CarRentalKey(id,customer.getCustomerID()));
        carRental.setStatus("Rented");
        iCarRentalService.update(carRental);
        mailService.sendSimpleMessage(customer.getEmail());
        return "successfully";
    }

    @PostMapping({"/customer/rent-car"})
    public String userRentCar(@ModelAttribute CarRentalRequest carRental, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("user");
        if (cus == null) {
            redirectAttributes.addFlashAttribute("err", "Please sign in to continue");
        } else {
            double price;
            CarRental entity = carRental.toCarRental();
            long rentalPeriodInMillis = entity.getReturnDate().getTime() - entity.getPickupDate().getTime();
            double rentalPeriodInDays = (double) rentalPeriodInMillis / (1000 * 60 * 60 * 24);
            if(rentalPeriodInDays >= 30){
                price = rentalPeriodInDays * entity.getCar().getRentPrice() *0.3;
            }
             else{
                 price = rentalPeriodInDays * entity.getCar().getRentPrice();
            }
            if (price == 0) {
                price = entity.getCar().getRentPrice();
            }
            model.addAttribute("car", entity);
            model.addAttribute("payment", price);
            entity.setStatus("In payment");
            entity.setRentPrice(price);
            iCarRentalService.update(entity);
            return "redirect:" + paymentService.getPaymentURL(price, Utils.getIpAddress(request), entity.getCar().getCarId());
        }

//        System.out.println("::: "+ carRental.toString())
        return "redirect:/login";
    }


    @GetMapping("/customer/review")
    public String loadDataForReview(Model model,
                                    @RequestParam int id, HttpServletRequest request) {
        Review review = new Review();
        HttpSession session = request.getSession();
        Car car = iCarService.findByID(id);
        Customer customer = (Customer) session.getAttribute("user");
        review.setCar(car);
        review.setCustomer(customer);
        model.addAttribute("review", review);
        return "customerWriteReview";
    }

    @PostMapping("/customer/review")
    public String createReview(@ModelAttribute Review review) {
        review.setId(new ReviewKey(review.getCustomer().getCustomerID(), review.getCar().getCarId()));
        iReviewService.save(review);
        return "redirect:/carRented";
    }

}
