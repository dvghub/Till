package nu.educom.warehouse.till.orders;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.products.IProduct;

import java.util.Map;

public interface IOrderHandler {
    int insertOrder(Money total);

    void insertOrderProducts(int order_id, Map<IProduct, Integer> purchases);
}
