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
    private PriceRate eightHourRate;
    private PriceRate dateRate;
    private PriceRate monthRate;

    public static CarPricingDTO fromCar(Car car) {
        return new CarPricingDTO(
                car,
                PriceRate.getRate(car.getRentPrice(),8,"$","$3/hour fuel surcharges"),
                PriceRate.getRate(car.getRentPrice(),24,"$","$3/hour fuel surcharges"),
                PriceRate.getRate(car.getRentPrice(), 24*30,"$","$3/hour fuel surcharges")
        );
    }
}
