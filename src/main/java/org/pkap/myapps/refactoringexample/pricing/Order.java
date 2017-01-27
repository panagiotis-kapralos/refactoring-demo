package org.pkap.myapps.refactoringexample.pricing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {

    private final List<Product> products;

    public Order(String... productCodes) {
        this.products = Stream.of(productCodes).map(Product::new).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return products;
    }

}
