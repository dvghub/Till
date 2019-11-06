package nu.educom.warehouse.till.till;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.display.TestDisplay;
import nu.educom.warehouse.till.products.TestProductCatalog;

import org.junit.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TillTest {

    @Test
    public void TestConstructor() {
        // Prepare

        // Run
        var sut = new Till(new TestProductCatalog()); // SUT = Software Under Test

        // Analyse
        Assert.assertNotNull(sut); // We expect that 'SUT' is a valid object
        assertThat(sut, instanceOf(ITill.class)); // We expect that it implements the ITill interface
    }

    @Test(expected = NullPointerException.class)
    public void TestEmptyConstructor() {
        new Till(null); // We expect that it throws an exception
    }

    @Test
    public void testHandleBarcode() {
        TestProductCatalog catalog = new TestProductCatalog();
        TestDisplay display = new TestDisplay();
        var sut = new Till(catalog);
        sut.setDisplayInterface(display);
        var result = sut.handleBarcode("1");

        Assert.assertTrue(result);
        Assert.assertEquals("Total: E 1,00", display.recievedLine1);
        Assert.assertEquals("Test product #1", display.recievedLine2);
    }

    @Test
    public void testInitiatePayment() {
        TestProductCatalog catalog = new TestProductCatalog();
        TestDisplay display = new TestDisplay();

        var sut = new Till(catalog);
        sut.setDisplayInterface(display);
        sut.handleBarcode("1");

        var result = sut.initiatePayment(new Money(2));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals((int) result.get(new Money(1)), 1);
    }

    @Test
    public void TestShowAllProducts() {
        // Prepare
        TestProductCatalog catalog = new TestProductCatalog();
        TestDisplay display = new TestDisplay();

        var sut = new Till(catalog); // SUT = Software Under Test
        sut.setDisplayInterface(display);

        // Run
        sut.showAllProducts();

        // Analyse
        Assert.assertNotNull(display.receivedProducts); // The function ITillDisplay.ShowProducts is called
        Assert.assertEquals(2, display.receivedProducts.size()); // The function is supplied with 2 products
        Assert.assertEquals(catalog.listToReturn, display.receivedProducts); // It should be the 2 products from the catalog
    }

    // TODO add more tests here
}
