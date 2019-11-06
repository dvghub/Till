package nu.educom.warehouse.till.products;

import nu.educom.calculateChange.Money;
import org.junit.*;

public class ProductCatalogTest {

    @Test
    public void testGetAllProducts() {
        // Prepare
        var sut = new ProductCatalog();

        // Run
        var result = sut.getAllProducts();

        // Analyze
        Assert.assertNotNull(result);
        Assert.assertEquals(4, result.size());
        Assert.assertEquals("0000", result.get(0).getBarcode());
    }

    @Test
    public void testFindProductForBarcode() {
        var sut = new ProductCatalog();
        var barcode = "0000";

        var result = sut.findProductForBarcode(barcode);

        Assert.assertNotNull(result);
        Assert.assertEquals(barcode, result.getBarcode());
        Assert.assertEquals("Product 0", result.getDescription());
        Assert.assertEquals(new Money(1), result.getPrice());
    }
}
