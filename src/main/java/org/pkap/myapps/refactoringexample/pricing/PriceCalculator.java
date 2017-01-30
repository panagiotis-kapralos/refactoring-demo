package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PriceCalculator {

    private PriceList priceList;
    private Map<String, BigDecimal> vatRates = new HashMap<>();

    public PriceCalculator(PriceList priceList) {
        this.priceList = priceList;
        vatRates.put("GR", new BigDecimal("0.24"));
        vatRates.put("DE", new BigDecimal("0.19"));
        vatRates.put("FR", new BigDecimal("0.20"));
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
        BigDecimal vatRate = getVatRateFor(countryCode);
        productPrice = productPrice.add(productPrice.multiply(vatRate)
                .setScale(2, RoundingMode.HALF_UP));
        return productPrice;
    }

    private BigDecimal getVatRateFor(String countryCode) {
        if (!vatRates.containsKey(countryCode)) {
            throw new UnsupportedCountryException(countryCode);
        }
        return vatRates.get(countryCode);
    }

}
