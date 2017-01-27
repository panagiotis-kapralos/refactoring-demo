package org.pkap.myapps.refactoringexample.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceList {

    private final Map<String, BigDecimal> prices = new HashMap<>();

    public PriceList(String... pricesArray) {
        for (int i=0; i<pricesArray.length; i+=2) {
            prices.put(pricesArray[i], new BigDecimal(pricesArray[i+1]));
        }
    }

    BigDecimal getPriceFor(Product product) {
        return prices.get(product.getCode());
    }

}
