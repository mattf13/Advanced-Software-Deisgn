package it.unibz.product.aom;

import java.util.Arrays;

public class StringListValidator implements PropertyValidator {

    private String[] validValues;

    public StringListValidator(String... validValues) {
        this.validValues = validValues;
    }

    @Override
    public boolean isValid(Object object) {
        return Arrays.stream(validValues).anyMatch(value -> value.equals(object));
    }
}
