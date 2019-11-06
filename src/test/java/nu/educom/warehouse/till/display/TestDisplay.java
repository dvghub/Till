package nu.educom.warehouse.till.display;

import nu.educom.warehouse.till.products.IProduct;

import java.util.List;

/**
 * Test implementation of the ITill interface for JUnit testing
 */
public class TestDisplay implements ITillDisplay {
    private String receivedTitle;
    public List<IProduct> receivedProducts;
    public String recievedLine1;
    public String recievedLine2;

    @Override
    public void displayProducts(String title, List<IProduct> products) {
        receivedTitle = title;
        receivedProducts = products;
    }

    @Override
    public void displayClientScreen(String line1, String line2) {
        recievedLine1 = line1;
        recievedLine2 = line2;
    }
}
