package org.pkap.myapps.refactoringexample.pricing;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PriceCalculatorTest {

    private PriceList priceList = new PriceList(
            "P1", "100.00",
            "P2", "50.00",
            "P3", "42.17"
    );

    private PriceCalculator calc = new PriceCalculator(priceList);

    @Test
    public void testCalculatePriceGreece() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "GR", new BigDecimal("0.00"));

        assertEquals("238.29", price.toPlainString());
    }

    @Test
    public void testCalculatePriceGermany() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "DE", new BigDecimal("0.00"));

        assertEquals("228.68", price.toPlainString());
    }

    @Test
    public void testCalculatePriceFranceWithDiscount() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "FR", new BigDecimal("0.33"));

        assertEquals("154.50", price.toPlainString());
    }

}
