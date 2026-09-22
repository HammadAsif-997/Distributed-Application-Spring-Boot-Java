package com.example.Distributed.Application.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PriceCalculationService {
    
    @Value("${app.currency.default}")
    private String defaultCurrency;
    
    public static final BigDecimal VOUCHER_PERCENTAGE = BigDecimal.valueOf(10); // 10%
    /**
     * Rounds a price to 2 decimal places using RoundingMode.HALF_UP.
     *8
     * @param price the price to be rounded.
     * @return the rounded price.
     */
    public BigDecimal roundPrice(BigDecimal price) {
        return price.setScale(2, RoundingMode.HALF_UP);
    }


    public PriceCalculationService(@Value("${app.currency.default}") String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public BigDecimal convertCurrency(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        BigDecimal conversionRate;
    
        if (fromCurrency == Currency.EUR && toCurrency == Currency.USD) {
            conversionRate = BigDecimal.valueOf(1.1); // EUR to USD
        } else if (fromCurrency == Currency.USD && toCurrency == Currency.EUR) {
            conversionRate = BigDecimal.valueOf(0.91); // USD to EUR
        } else {
            return amount; // Same currency, no conversion
        }
    
        return amount.multiply(conversionRate).setScale(2, RoundingMode.HALF_UP);
    }

    
    

    public BigDecimal applyVoucher(BigDecimal price, BigDecimal percentage) {
        BigDecimal discount = price.multiply(percentage).divide(BigDecimal.valueOf(100));
        return price.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    
}
