package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private PriceList priceList;
    private VatRates vatRates = new VatRates();

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
        BigDecimal vatRate = vatRates.getVatRateFor(countryCode);
        productPrice = productPrice.add(productPrice.multiply(vatRate)
                .setScale(2, RoundingMode.HALF_UP));
        return productPrice;
    }

}
