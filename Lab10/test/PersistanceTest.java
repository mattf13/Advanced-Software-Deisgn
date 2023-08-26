import it.unibz.product.aom.PersistenceService;
import it.unibz.product.aom.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersistenceTest {
    private PersistenceService persistenceService;

    @Before
    public void setup() {
        persistenceService = new PersistenceService();
    }

    @Test
    public void testSave() {
        Product product = new Product(new ProductType("TestType"));
        persistenceService.save(product);
    }

    @Test
    public void testUpdate() {
        Product product = new Product(new ProductType("TestType"));
        persistenceService.save(product);

        product.setProperty("name", "Test");
        persistenceService.update(product);
    }

    @Test
    public void testRetrieve() {
        Product product = new Product(new ProductType("TestType"));
        persistenceService.save(product);

        Product retrievedProduct = (Product) persistenceService.retrieve(Product.class, product.getId());
        assertEquals(product, retrievedProduct);
    }
}

