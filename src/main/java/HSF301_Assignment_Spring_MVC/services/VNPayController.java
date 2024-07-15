package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.services.config.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VNPayController {
        @Autowired
        private VNPAYService vnPayService;

//        @GetMapping({"", "/"})
//        public String home(){
//            return "createOrder";
//        }
//
//        // Chuyển hướng người dùng đến cổng thanh toán VNPAY
//        @PostMapping("/submitOrder")
//        public String submidOrder(@RequestParam("amount") int orderTotal,
//                                  @RequestParam("orderInfo") String orderInfo,
//                                  HttpServletRequest request){
//            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//            String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);
//            return "redirect:" + vnpayUrl;
//        }

        // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
        @GetMapping("/vnpay-payment-return")
        public String paymentCompleted(HttpServletRequest request, Model model){
            int paymentStatus =vnPayService.orderReturn(request);

            String orderInfo = request.getParameter("vnp_OrderInfo");
            String paymentTime = request.getParameter("vnp_PayDate");
            String transactionId = request.getParameter("vnp_TransactionNo");
            String totalPrice = request.getParameter("vnp_Amount");

            model.addAttribute("orderId", orderInfo);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("paymentTime", paymentTime);
            model.addAttribute("transactionId", transactionId);

            return paymentStatus == 1 ? "ordersuccess" : "orderfail";
        }
}
