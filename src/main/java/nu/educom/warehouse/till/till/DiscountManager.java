package nu.educom.warehouse.till.till;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.products.IProduct;
import nu.educom.warehouse.till.products.Product;

import java.util.*;

public class DiscountManager implements IDiscountManager {
    private final Map<IProduct, Integer> purchases = new HashMap<>();
    private int aCounter = 0;
    private int bCounter = 0;
    private int cCounter = 0;
    private int dCounter = 0;
    private IProduct a = new Product();
    private IProduct b = new Product();
    private IProduct c = new Product();

    @Override
    public Money getDiscount(IProduct product) {
        Money result = Money.ZERO;
        Money k = new Money(2);
        int n = 5;
        int p = 30;

        if (purchases.containsKey(product)) {
            purchases.replace(product, purchases.get(product) + 1);
        } else {
            purchases.put(product, 1);
        }

        //DISCOUNTS -- uncomment to apply

        // DISCOUNT -- 3 items, 25% off
        //==============================================================================================================
//        for (Map.Entry<IProduct, Integer> purchase : purchases.entrySet()) {
//            if ((int) Math.floor(purchase.getValue() / 3.0) > aCounter) {
//                aCounter++;
//                result = product.getPrice().times(3.0);
//                result = result.times(.25);
//            }
//        }

        //DISCOUNT -- n of a | b, p % off
        //==============================================================================================================
//        if (purchases.containsKey(a) || purchases.containsKey(b)) {
//            int sum = purchases.getOrDefault(a, 0) + purchases.getOrDefault(b, 0);
//            if (sum >= n && sum/n > bCounter) {
//                bCounter++;
//                result = a.getPrice().times(purchases.getOrDefault(a, 0)).add(b.getPrice().times(purchases.getOrDefault(b, 0)));
//                result = result.times(p / 100.0);
//            }
//        }

        //DISCOUNT -- n of a | b | c, cheapest item off
        //==============================================================================================================
//        if (purchases.containsKey(a) || purchases.containsKey(b) || purchases.containsKey(c)) {
//            int sum = purchases.getOrDefault(a, 0) + purchases.getOrDefault(b, 0) + purchases.getOrDefault(c, 0);
//            if (sum >= n && sum/n > cCounter) {
//                cCounter ++;
//                List<Money> prices = new ArrayList<>();
//                if (purchases.containsKey(a)) prices.add(a.getPrice().times(1));
//                if (purchases.containsKey(b)) prices.add(b.getPrice().times(1));
//                if (purchases.containsKey(c)) prices.add(c.getPrice().times(1));
//
//                prices.sort((money, money2) -> (int) money.subtract(money2).divide(new Money(1)));
//
//                result = prices.get(0);
//            }
//        }

        //DISCOUNT -- a && b, (a + b) - k off
        //==============================================================================================================
//        if (purchases.containsKey(a) && purchases.containsKey(b)) {
//            int lowest = purchases.get(a) < purchases.get(b) ? purchases.get(a) : purchases.get(b);
//            if (lowest > dCounter) {
//                dCounter ++;
//
//                result = a.getPrice().add(b.getPrice()).subtract(k);
//            }
//        }

        return result;
    }

    @Override
    public void clearPurchases() {
        purchases.clear();
    }

    @Override
    public void setProducts(IProduct a, IProduct b, IProduct c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
