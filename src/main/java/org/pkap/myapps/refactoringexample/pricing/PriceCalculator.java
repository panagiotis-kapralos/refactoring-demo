package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;

class PriceCalculator {
    private PriceList priceList;
    PriceCalculator(PriceList priceList) { this.priceList = priceList; }

    BigDecimal calculatePrice(Order order, String country) {
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Product product : order.getProducts()) {
            BigDecimal productPrice = calculatePriceWithVat(product, country);
            totalPrice = totalPrice.add(productPrice);
        }
        return totalPrice;
    }

    private BigDecimal calculatePriceWithVat(Product product, String country) {
        BigDecimal productPrice = priceList.getPriceFor(product);
        if ("GR".equals(country)) {
            productPrice = productPrice.add(productPrice.multiply(
                    new BigDecimal("0.24")).setScale(2, HALF_UP));
        } else if ("DE".equals(country)) {
            productPrice = productPrice.add(productPrice.multiply(
                    new BigDecimal("0.19")).setScale(2, HALF_UP));
        } else if ("FR".equals(country)) {
            productPrice = productPrice.add(productPrice.multiply(
                    new BigDecimal("0.20")).setScale(2, HALF_UP));
        } else {
            throw new UnsupportedCountryException(country);
        }
        return productPrice;
    }
}
