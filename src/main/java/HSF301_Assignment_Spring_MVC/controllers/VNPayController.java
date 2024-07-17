package HSF301_Assignment_Spring_MVC.controllers;

import HSF301_Assignment_Spring_MVC.dtos.PaymentDTO;
import HSF301_Assignment_Spring_MVC.dtos.ResponseDto;
import HSF301_Assignment_Spring_MVC.services.vnpay.PaymentService;
import HSF301_Assignment_Spring_MVC.services.vnpay.VNPAYService;
import HSF301_Assignment_Spring_MVC.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class VNPayController {
    private final PaymentService paymentService;


    @Autowired
    public VNPayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @GetMapping("/vnpay")
//    public ResponseDto<PaymentDTO> testPayment(@RequestParam(name = "amount", defaultValue = "0") double amount,
//                                               HttpServletRequest request){
//        return new ResponseDto<>(HttpStatus.OK, "Success", paymentService.paymentDto(amount, Utils.getIpAddress(request)));
//    }
}
