package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private PriceList priceList;
    private VatRates vatRates = new VatRates();

    public PriceCalculator(PriceList priceList) {
        this.priceList = priceList;
    }

    public BigDecimal calculatePrice(Order order, String countryCode, BigDecimal discountRate) {
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Product product : order.getProducts()) {
            BigDecimal productPrice = calculatePriceWithVat(product, countryCode);
            totalPrice = totalPrice.add(productPrice);
        }
        totalPrice = applyDiscount(totalPrice, discountRate);
        return totalPrice;
    }

    private BigDecimal calculatePriceWithVat(Product product, String countryCode) {
        BigDecimal productPrice = priceList.getPriceFor(product);
        BigDecimal vatRate = vatRates.getFor(countryCode);
        if (vatRate != null) {
            productPrice = productPrice.add(productPrice.multiply(vatRate).setScale(2, RoundingMode.HALF_UP));
        }
        return productPrice;
    }

    private BigDecimal applyDiscount(BigDecimal totalPrice, BigDecimal discountRate) {
        return totalPrice.subtract(totalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP));
    }

}
