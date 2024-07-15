package HSF301_Assignment_Spring_MVC.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceRate {
    private Double price;
    private String priceText;
    private String currencyUnit;
    private String surcharge;

    public static PriceRate getRate(double unitPrice, int hour, String currencyUnit, String surcharge) {
        double calculatedPrice = unitPrice * hour;
        BigDecimal roundedPrice = new BigDecimal(calculatedPrice).setScale(2, RoundingMode.UP);
        String formattedPrice = formatPrice(roundedPrice.doubleValue());

        return new PriceRate(
                roundedPrice.doubleValue(),
                formattedPrice,
                currencyUnit,
                surcharge
        );
    }

    private static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(price);
    }
}
