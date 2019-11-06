package nu.educom.warehouse.till.products;

import nu.educom.calculateChange.Money;

public class Product implements IProduct {
    private int id;
    private String barcode;
    private String description;
    private double price;
    private Money money_price;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
        return money_price != null ? money_price : new Money(price);
    }

    @Override
    public void setPrice(double price) {
        this.money_price = new Money(price);
    }
}
