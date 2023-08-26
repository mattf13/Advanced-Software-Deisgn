package it.unibz.product.aom;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ProductType type;

    private List<ProductProperty> productPropertyList;

    public Product(ProductType type) {
        this.type = type;
        productPropertyList = new ArrayList<>();
    }

    public Object getProperty(String name) {
        return productPropertyList.stream()
                .filter((prop) -> prop.getPropType()
                        .getName()
                        .equals(name))
                .findFirst()
                .get()
                .getValue();
    }

    private boolean hasPropertyAssigned(String name) {
        return productPropertyList.stream().anyMatch((prop) -> prop.getPropType().getName().equals(name));
    }

    public void setProperty(String name, Object value) {
        if(!type.getProductPropertyTypeByName(name).validate(value)){
            throw new ProductAOMException();
        }
        if (hasPropertyAssigned(name)) {
            productPropertyList.stream()
                    .filter((prop) -> prop.getPropType()
                            .getName()
                            .equals(name))
                    .findFirst()
                    .get()
                    .setValue(value);
        } else {
            ProductProperty prop = new ProductProperty(type.getProductPropertyTypeByName(name));
            if (!prop.getPropType().getType().isAssignableFrom(value.getClass())) {
                throw new ProductAOMException();
            }
            prop.setValue(value);
            productPropertyList.add(prop);
        }
    }
}
