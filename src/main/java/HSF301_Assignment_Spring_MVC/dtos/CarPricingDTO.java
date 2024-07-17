package HSF301_Assignment_Spring_MVC.dtos;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPricingDTO {
    private Car car;
    private PriceRate dateRate;
    private PriceRate monthRate;

    public static CarPricingDTO fromCar(Car car) {
        return new CarPricingDTO(
                car,
                PriceRate.getRate(car.getRentPrice(),1,"$","$3/hour fuel surcharges"),
                PriceRate.getRate(car.getRentPrice(), 30,"$","$3/hour fuel surcharges",0.3)
        );
    }
}
