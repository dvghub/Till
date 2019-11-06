package nu.educom.warehouse.till.products;

import nu.educom.calculateChange.Money;

public interface IProduct {
    int getId();

    void setId(int id);

    String getBarcode();

    void setBarcode(String barcode);

    String getDescription();

    void setDescription(String description);

    Money getPrice();

    void setPrice(double price);
}
