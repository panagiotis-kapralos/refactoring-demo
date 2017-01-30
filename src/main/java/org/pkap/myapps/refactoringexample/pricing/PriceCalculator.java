package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private PriceList priceList;

    public PriceCalculator(PriceList priceList) {
        this.priceList = priceList;
    }

    public BigDecimal calculatePrice(Order order, String countryCode) {
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Product product : order.getProducts()) {
            BigDecimal productPrice = calculatePriceWithVat(product, countryCode);
            totalPrice = totalPrice.add(productPrice);
        }
        return totalPrice;
    }

    private BigDecimal calculatePriceWithVat(Product product, String countryCode) {
        BigDecimal productPrice = priceList.getPriceFor(product);
        BigDecimal vatRate;
        if ("GR".equals(countryCode)) {
            vatRate = new BigDecimal("0.24");
            productPrice = productPrice.add(productPrice.multiply(
                    vatRate).setScale(2, RoundingMode.HALF_UP));
        } else if ("DE".equals(countryCode)) {
            vatRate = new BigDecimal("0.19");
            productPrice = productPrice.add(productPrice.multiply(
                    vatRate).setScale(2, RoundingMode.HALF_UP));
        } else if ("FR".equals(countryCode)) {
            vatRate = new BigDecimal("0.20");
            productPrice = productPrice.add(productPrice.multiply(
                    vatRate).setScale(2, RoundingMode.HALF_UP));
        } else {
            throw new UnsupportedCountryException(countryCode);
        }
        return productPrice;
    }

}
