package HSF301_Assignment_Spring_MVC.services.vnpay;

import HSF301_Assignment_Spring_MVC.dtos.PaymentDTO;
import HSF301_Assignment_Spring_MVC.pojos.CarRental;
import HSF301_Assignment_Spring_MVC.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
//    public PaymentDTO paymentDto(double amount, String ip) {
//        double price = amount * 2300000L;
//        String bankCode = "NCB";
//        Map<String, String> vnpParamsMap = Config.getVNPayConfig();
//        vnpParamsMap.put("vnp_Amount", String.valueOf(price));
//        vnpParamsMap.put("vnp_IpAddr", ip);
//        vnpParamsMap.put("vnp_BankCode", bankCode);
////build query url
//        String queryUrl = Utils.getPaymentURL(vnpParamsMap, true);
//        String hashData = Utils.getPaymentURL(vnpParamsMap, false);
//        String vnpSecureHash = Utils.hmacSHA512(Utils.getSecretKey(), hashData);
//        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
//        String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html" + "?" + queryUrl;
//        return PaymentDTO.builder()
//                .code("ok")
//                .message("success")
//                .paymentUrl(paymentUrl).build();
//
//    }

    public String getPaymentURL(double amount, String ip, int carId) {
        long price = (long) (amount * 2300000L);
        String bankCode = "NCB";
        Map<String, String> vnpParamsMap = Config.getVNPayConfig(carId);
//        Map<String, String> vnpParamsMap = Config.getVNPayConfig(carId);
        vnpParamsMap.put("vnp_Amount", String.valueOf(price));
        vnpParamsMap.put("vnp_IpAddr", ip);
        vnpParamsMap.put("vnp_BankCode", bankCode);
//build query url
        String queryUrl = Utils.getPaymentURL(vnpParamsMap, true);
        String hashData = Utils.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = Utils.hmacSHA512(Utils.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html" + "?" + queryUrl;
        return paymentUrl;

    }
}
