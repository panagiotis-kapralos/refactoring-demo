package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private PriceList priceList;

    public PriceCalculator(PriceList priceList) {
        this.priceList = priceList;
    }

    public BigDecimal calculatePrice(Order order, String countryCode, BigDecimal discountRate) {
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Product product : order.getProducts()) {
            BigDecimal productPrice = priceList.getPriceFor(product);
            if ("GR".equals(countryCode)) {
                productPrice = productPrice.add(productPrice.multiply(new BigDecimal("0.24")).setScale(2, RoundingMode.HALF_UP));
            } else if ("DE".equals(countryCode)) {
                productPrice = productPrice.add(productPrice.multiply(new BigDecimal("0.19")).setScale(2, RoundingMode.HALF_UP));
            } else if ("FR".equals(countryCode)) {
                productPrice = productPrice.add(productPrice.multiply(new BigDecimal("0.20")).setScale(2, RoundingMode.HALF_UP));
            }
            totalPrice = totalPrice.add(productPrice);
        }
        totalPrice = totalPrice.subtract(totalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP));
        return totalPrice;
    }

}
