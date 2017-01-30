package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VatRates {

    private Map<String, BigDecimal> vatRates = new HashMap<>();

    public VatRates() {
        vatRates.put("GR", new BigDecimal("0.24"));
        vatRates.put("DE", new BigDecimal("0.19"));
        vatRates.put("FR", new BigDecimal("0.20"));
    }

    public BigDecimal getVatRateFor(String countryCode) {
        if (!vatRates.containsKey(countryCode)) {
            throw new UnsupportedCountryException(countryCode);
        }
        return vatRates.get(countryCode);
    }

}
