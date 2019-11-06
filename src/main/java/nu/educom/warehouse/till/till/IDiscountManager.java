package nu.educom.warehouse.till.till;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.products.IProduct;

interface IDiscountManager {
    Money getDiscount(IProduct product);

    void clearPurchases();

    void setProducts(IProduct a, IProduct b, IProduct c);
}

