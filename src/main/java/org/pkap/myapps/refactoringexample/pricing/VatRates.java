package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class VatRates {
    private Map<String, BigDecimal> vatRates = new HashMap<>();

    VatRates() {
        vatRates.put("GR", new BigDecimal("0.24"));
        vatRates.put("DE", new BigDecimal("0.19"));
        vatRates.put("FR", new BigDecimal("0.20"));
    }

    BigDecimal getVatRateFor(String country) {
        if (!vatRates.containsKey(country)) {
            throw new UnsupportedCountryException(country);
        }
        return vatRates.get(country);
    }
}
