import it.unibz.product.aom.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AOMTest {
    @Test
    public void perfectEntity() {
        ProductType type = new ProductType("TShirt");
        type.addPropType(new ProductPropertyType("size", Integer.class));
        type.addPropType(new ProductPropertyType("color", String.class));

        Product p = new Product(type);
        p.setProperty("size", 48);
        p.setProperty("color", "BLUE");

        assertEquals(48, p.getProperty("size"));
        assertEquals("BLUE", p.getProperty("color"));
    }

    @Test
    public void settingAPropertyThatDoesNotExist() {
        ProductType type = new ProductType("TShirt");
        type.addPropType(new ProductPropertyType("size", Integer.class));
        type.addPropType(new ProductPropertyType("color", String.class));

        Product p = new Product(type);
        assertThrows(ProductAOMException.class, () -> {
            p.setProperty("battery", "10hrs");
        });
    }

    @Test
    public void settingAValueThatDoesNotBelongToTheType() {
        ProductType type = new ProductType("TShirt");
        type.addPropType(new ProductPropertyType("size", Integer.class));
        type.addPropType(new ProductPropertyType("color", String.class));

        Product p = new Product(type);
        assertThrows(ProductAOMException.class, () -> {
            p.setProperty("size", "XXL");
        });
    }

    @Test
    public void settingPropertyTwice() {
        ProductType type = new ProductType("TShirt");
        type.addPropType(new ProductPropertyType("size", Integer.class));
        type.addPropType(new ProductPropertyType("color", String.class));

        Product p = new Product(type);
        p.setProperty("size", 48);
        p.setProperty("size", 50);

        assertEquals(50, p.getProperty("size"));
    }

    @Test
    public void addPropertyTypeTwice() {
        ProductType type = new ProductType("TShirt");
        type.addPropType(new ProductPropertyType("size", Integer.class));
        type.addPropType(new ProductPropertyType("color", String.class));
        assertThrows(ProductAOMException.class, () -> {
            type.addPropType(new ProductPropertyType("color", String.class));
        });
    }

    @Test
    public void addPropertyNotValid() {
        ProductType type = new ProductType("TShirt");
        ProductPropertyType propType = new ProductPropertyType("color", String.class);
        propType.addValidator(new StringListValidator("BLUE", "RED", "GREEN"));
        type.addPropType(propType);
        Product p = new Product(type);
        p.setProperty("color", "BLUE");
        assertThrows(ProductAOMException.class, () -> {
            p.setProperty("color", "WHITE");
        });
    }
}
