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
            BigDecimal productPrice = calculatePriceWithVat(product, countryCode);
            totalPrice = totalPrice.add(productPrice);
        }
        totalPrice = applyDiscount(totalPrice, discountRate);
        return totalPrice;
    }

    private BigDecimal calculatePriceWithVat(Product product, String countryCode) {
        BigDecimal productPrice = priceList.getPriceFor(product);
        BigDecimal vatRate = getVatRateFor(countryCode);
        if (vatRate != null) {
            productPrice = productPrice.add(productPrice.multiply(vatRate).setScale(2, RoundingMode.HALF_UP));
        }
        return productPrice;
    }

    private BigDecimal getVatRateFor(String countryCode) {
        BigDecimal vatRate = null;
        if ("GR".equals(countryCode)) {
            vatRate = new BigDecimal("0.24");
        } else if ("DE".equals(countryCode)) {
            vatRate = new BigDecimal("0.19");
        } else if ("FR".equals(countryCode)) {
            vatRate = new BigDecimal("0.20");
        }
        return vatRate;
    }

    private BigDecimal applyDiscount(BigDecimal totalPrice, BigDecimal discountRate) {
        return totalPrice.subtract(totalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP));
    }

}
