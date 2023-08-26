package it.unibz.product.aom;

import java.util.ArrayList;
import java.util.List;

public class ProductType {
    private List<ProductPropertyType> productPropertyTypeList;
    private String typeName;

    public ProductType(String typeName) {
        this.typeName = typeName;
        productPropertyTypeList = new ArrayList<>();
    }
    public void addPropType(ProductPropertyType productPropertyType){
        if (productPropertyTypeList.stream().anyMatch(pT->pT.getName().equals(productPropertyType.getName()))){
            throw new ProductAOMException();
        }
        productPropertyTypeList.add(productPropertyType);
    }

    public List<ProductPropertyType> getProductPropertyTypeList() {
        return productPropertyTypeList;
    }
    public ProductPropertyType getProductPropertyTypeByName(String name){
        return productPropertyTypeList.stream().filter
                ((ppType) -> ppType.getName().equals(name)).findFirst()
                .orElseThrow(()-> new ProductAOMException());
    }
}
