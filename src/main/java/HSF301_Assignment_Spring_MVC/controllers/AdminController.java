package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.pojos.*;
import HSF301_Assignment_Spring_MVC.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ICarService iCarService;
    private ICustomerService iCustomerService;
    private ICarRentalService iCarRentalService;
    private IReviewService iReviewService;
    private IAccountService iAccountService;

    @Autowired
    public AdminController(ICarService iCarService, ICustomerService iCustomerService, ICarRentalService iCarRentalService,IReviewService iReviewService,IAccountService iAccountService) {
        this.iCarService = iCarService;
        this.iAccountService = iAccountService;
        this.iCustomerService = iCustomerService;
        this.iCarRentalService = iCarRentalService;
        this.iReviewService = iReviewService;
    }

    @GetMapping("/carAdmin")
    public String getCars(Model model){
        List<Car> carList = iCarService.getAll();
        model.addAttribute("listCar",carList);
        return "carAdmin";
    }


    @PostMapping("/carAdmin")
    public String removeCars(Model model ,@RequestParam(value ="id", required = false, defaultValue = "0") int id){
        if(id!= 0){
            try {
                System.out.println("Attempting to delete car with ID: " + id);
                Car car = iCarService.findByID(id);
                System.out.println("Car: "+car);
                iCarService.delete(car);
                System.out.println("Delete car successfully");
                model.addAttribute("notification","Delete car successfully !");
            }catch (Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
        }
        List<Car> carList = iCarService.getAll();
        model.addAttribute("listCar",carList);
        return "carAdmin";
    }

    @GetMapping("/customer")
    public String getCustomer(Model model,@RequestParam(value ="id", required = false, defaultValue = "0") int id){
        List<Customer> customerList = iCustomerService.getByAccountRole("Customer");
        model.addAttribute("customer",customerList);
        return "customer";
    }


    @PostMapping("/customer")
    public String removeCustomer(
            Model model,
            @RequestParam(value ="id", required = false, defaultValue = "0") int id){
        if(id!= 0){
            try {
                iCustomerService.delete(Integer.valueOf(id));
                model.addAttribute("notification","Delete customer successfully !");
            }catch (Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
        }
        List<Customer> customerList = iCustomerService.getByAccountRole("Customer");
        model.addAttribute("customer",customerList);
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
        model.addAttribute("review", reviews);
        return "review";
    }

    @PostMapping("/review")
    public String deleteReview(Model model, @RequestBody ReviewKey id) {

        if(id != null){
            try {
                iReviewService.delete(id);
                model.addAttribute("notification","Delete review successfully !");
            }catch (Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
        }

        List<Review> reviews = iReviewService.getAll();
        model.addAttribute("review", reviews);
        return "review";
    }

    @GetMapping("/createCar")
    public String createNewCar(Model model){
        Car car = new Car();
        model.addAttribute("car",car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String saveCar(@Validated @ModelAttribute Car car , BindingResult result, Model model){

        try {
            iCarService.save(car);
            System.out.println("Save car successfully !");
            model.addAttribute("notification","Save car successfully !");
        }catch (Exception ex) {
            System.out.printf("Error :" + ex.getMessage());
            return "CreateCar";
        }
        List<Car> carList = iCarService.getAll();
        model.addAttribute("listCar", carList);
        return "carAdmin";
    }

    @GetMapping("/updateCar")
    public String showUpdateCar(Model model, @RequestParam("id") int id){
        Car car = iCarService.findByID(id);
        System.out.println("Car: "+car);
        model.addAttribute("car",car);
        return "UpdateCar";
    }

    @PostMapping("/updateCar")
    public String updateCar (@Validated @ModelAttribute("car") Car car , Model model, @RequestParam("id") int id){
        try {
            System.out.println("Hello");
            Car carToUpdate = iCarService.findByID(id);
            carToUpdate.setCarName(car.getCarName());
            carToUpdate.setCarModelYear(car.getCarModelYear());
            carToUpdate.setImgLink(car.getImgLink());
            carToUpdate.setCapacity(car.getCapacity());
            carToUpdate.setColor(car.getColor());
            carToUpdate.setDescription(car.getDescription());
            carToUpdate.setImportDate(car.getImportDate());
            carToUpdate.setTransmission(car.isTransmission());
            carToUpdate.setStatus(car.getStatus());
            carToUpdate.setProducer(car.getProducer());
            carToUpdate.setRentPrice(car.getRentPrice());
            carToUpdate.setCategoryID(car.getCategoryID());
            iCarService.update(carToUpdate);
            System.out.println("Update car successfully !");
            model.addAttribute("notification","Update car successfully !");
        }catch (Exception ex){
            System.out.println("Error: "+ ex.getMessage()   );
        }
        List<Car> carList = iCarService.getAll();
        model.addAttribute("listCar", carList);
        return "carAdmin";
    }
}
