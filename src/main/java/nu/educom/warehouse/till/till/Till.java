package nu.educom.warehouse.till.till;

import nu.educom.calculateChange.CashRegister;
import nu.educom.calculateChange.Money;
import nu.educom.calculateChange.ReverseComparer;
import nu.educom.warehouse.till.display.ITillDisplay;
import nu.educom.warehouse.till.orders.IOrderHandler;
import nu.educom.warehouse.till.orders.OrderHandler;
import nu.educom.warehouse.till.products.IProduct;
import nu.educom.warehouse.till.products.IProductCatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Till implements ITill {
    private final IProductCatalog catalog;
    private ITillDisplay display;
    private final CashRegister register;
    private final Map <IProduct, Integer> purchases = new HashMap<>();
    private final IDiscountManager discountManager = new DiscountManager();
    private Money total = new Money(0);

    public Till(IProductCatalog productCatalogus) {
        this.catalog = productCatalogus;
        discountManager.setProducts(catalog.findProductForBarcode("23"), catalog.findProductForBarcode("57"), catalog.findProductForBarcode("39"));

        Map<Money, Integer> initialContent = new TreeMap<>(new ReverseComparer());
        initialContent.put( new Money(50), 0);
        initialContent.put( new Money(20), 5);
        initialContent.put( new Money(10), 5);
        initialContent.put( new Money( 5), 7);
        initialContent.put( new Money( 2), 12);
        initialContent.put( new Money( 1), 15);
        initialContent.put( new Money( 0.5), 22);
        initialContent.put( new Money( 0.2), 10);
        initialContent.put( new Money( 0.1), 3);
        initialContent.put( new Money( 0.05), 7);
        initialContent.put( new Money( 0.02), 1);
        initialContent.put( new Money( 0.01), 5);

        register = new CashRegister(initialContent);
    }

    @Override
    public void setDisplayInterface(ITillDisplay tillDisplay) {
        this.display = tillDisplay;
    }

    @Override
    public boolean handleBarcode(String barcode) {
        IProduct product = catalog.findProductForBarcode(barcode);
        if (product != null) {
            if (purchases.containsKey(product)) {
                purchases.replace(product, purchases.get(product) + 1);
            } else {
                purchases.put(product, 1);
            }
            total = total.add(product.getPrice());
            total = total.subtract(discountManager.getDiscount(product));
            display.displayClientScreen("Total: " + total.toString(), product.getDescription());
            return true;
        } else return false;
    }

    @Override
    public Map<Money, Integer> initiatePayment(Money amount) {
        Map<Money, Integer> change = register.makeChange(total, amount);
        if (change != null) {
            IOrderHandler handler = new OrderHandler();
            int order_id = handler.insertOrder(total);
            handler.insertOrderProducts(order_id, purchases);
            total = new Money(0);
            purchases.clear();
            discountManager.clearPurchases();
        }
        return change;
    }

    @Override
    public void showAllProducts() {
        List<IProduct> products = catalog.getAllProducts();
        display.displayProducts("Products", products);
    }
}
