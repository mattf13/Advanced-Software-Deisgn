package it.unibz.product.aom;

public class ProductProperty {
    private ProductPropertyType propType;
    private Object value;

    public ProductProperty(ProductPropertyType propType) {
        this.propType = propType;
        value = null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ProductPropertyType getPropType() {
        return propType;
    }

}
