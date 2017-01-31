package org.pkap.myapps.refactoringexample.pricing;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PriceCalculatorTest {

    private PriceList priceList = new PriceList(
            "P1", "5.73",
            "P2", "9.98",
            "P3", "10.73"
    );

    private PriceCalculator calc = new PriceCalculator(priceList);

    @Test
    public void testCalculatePriceGreece() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "GR");

        assertEquals("32.80", price.toPlainString());
    }

    @Test
    public void testCalculatePriceGermany() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "DE");

        assertEquals("31.47", price.toPlainString());
    }

    @Test
    public void testCalculatePriceFrance() {
        Order order = new Order("P1", "P2", "P3");
        BigDecimal price = calc.calculatePrice(order, "FR");

        assertEquals("31.74", price.toPlainString());
    }

    @Test(expected=UnsupportedCountryException.class)
    public void testCalculatePriceForUnsupportedCountry() {
        Order order = new Order("P1");
        calc.calculatePrice(order, "XX");
    }

}
