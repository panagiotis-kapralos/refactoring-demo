package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
        BigDecimal vatRate = getVatRateFor(country);
        productPrice = productPrice.add(productPrice.multiply(vatRate).setScale(2, HALF_UP));
        return productPrice;
    }

    private BigDecimal getVatRateFor(String country) {
        Map<String, BigDecimal> vatRates = new HashMap<>();
        vatRates.put("GR", new BigDecimal("0.24"));
        vatRates.put("DE", new BigDecimal("0.19"));
        vatRates.put("FR", new BigDecimal("0.20"));
        if (!vatRates.containsKey(country)) {
            throw new UnsupportedCountryException(country);
        }
        return vatRates.get(country);
    }
}
