package HSF301_Assignment_Spring_MVC.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {
    public String code;
    public String message;
    public String paymentUrl;
}
