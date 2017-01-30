package org.pkap.myapps.refactoringexample.pricing;

public class UnsupportedCountryException extends RuntimeException {

    public UnsupportedCountryException(String countryCode) {
        super("Unsupported country " + countryCode);
    }

}
