package it.unibz.product.aom;

public class NoneValidation implements PropertyValidator{
    @Override
    public boolean isValid(Object object) {
        return true;
    }
}
