package it.unibz.product.aom;

public class ProductPropertyType {
    private String name;
    private Class<?> type;
    private PropertyValidator pv = new NoneValidation();

    public ProductPropertyType(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public void addValidator(PropertyValidator pv){
        this.pv = pv;
    }
    public String getName() {
        return name;
    }

    public boolean validate(Object value) {
        return pv.isValid(value);
    }

    public Class<?> getType() {
        return type;
    }
}
