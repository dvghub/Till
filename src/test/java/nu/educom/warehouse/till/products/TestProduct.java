package nu.educom.warehouse.till.products;

import nu.educom.calculateChange.Money;

/**
 * Test implementation of the IProduct interface for JUnit testing
 */

public class TestProduct implements IProduct {
    private String barcode;
    private String description;
    private Money amount;

    /**
     * Test product class, only to be used in JUnit tests
     *
     * @param testProductId the test product id.
     */
    public TestProduct(int testProductId) {
        this.barcode = Integer.toString(testProductId);
        this.description = String.format("Test product #%d", testProductId);
        this.amount = new Money(testProductId);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Money getPrice() {
        return amount;
    }

    @Override
    public void setPrice(double price) {
        this.amount = new Money(price);
    }
}
