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
        BigDecimal vatRate;
        if ("GR".equals(country)) {
            vatRate = new BigDecimal("0.24");
        } else if ("DE".equals(country)) {
            vatRate = new BigDecimal("0.19");
        } else if ("FR".equals(country)) {
            vatRate = new BigDecimal("0.20");
        } else {
            throw new UnsupportedCountryException(country);
        }
        productPrice = productPrice.add(productPrice.multiply(vatRate).setScale(2, HALF_UP));
        return productPrice;
    }
}
