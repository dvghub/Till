package nu.educom.calculateChange;

import java.util.Map;
import java.util.TreeMap;

public class CashRegister {

    private final Map <Money, Integer> content;
    private Money total;
    private final double [] values = {50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};

    public CashRegister(Map<Money, Integer> initialContent) {
        this.content = initialContent;
        this.total = countRegister();
    }

    public Map<Money, Integer> makeChange(Money toPay, Money paid) {
        Map<Money, Integer> toReturn = new TreeMap<>(new ReverseComparer());
        Money change = paid.subtract(toPay);

        //Enable to see changes in register
        boolean debug = false;
        if (debug) System.out.println("Register before paid: " + content);
        addToRegister(paid);

        for (Money current : content.keySet()) {
            int x = (int) Math.floor(change.divide(current));
            int i = content.get(current);

            if (x <= 0) continue;

            while (x > 0 && i > 0) {
                if (toReturn.containsKey(current)) {
                    toReturn.replace(current, toReturn.get(current) + 1);
                } else {
                    toReturn.put(current, 1);
                }
                change = change.subtract(current);
                x --;
                i--;
            }
        }

        Money threshold = new Money(0.05);
        if (change.compareTo(Money.ZERO) != 0 && change.compareTo(threshold) < 0) {
            if (content.get(threshold) > 0) {
                toReturn.remove(new Money(0.02));
                toReturn.remove(new Money(0.01));
                if (change.compareTo(new Money(0.03)) >= 0) {
                    if (toReturn.containsKey(threshold)) {
                        toReturn.replace(threshold, toReturn.get(threshold) + 1);
                    } else {
                        toReturn.put(threshold, 1);
                    }
                }
            } else {
                return null;
            }
        }
        if (debug) System.out.println("Register after paid: " + content);
        if (debug) System.out.println("Change to return: " + toReturn);
        removeFromRegister(toReturn);
        if (debug) System.out.println("Content after change: " + content);
        return toReturn;
    }

    private void addToRegister(Money money) {
        double big = Math.floor(money.divide(new Money(1)));
        double decimals = money.remainder(new Money(1)) / 100;

        for (double value : values) {
            Money current = new Money(value);

            int x = (int) Math.floor(big / value);
            if (x > 0) {
                int amount = content.get(current);
                content.replace(current, amount + x);
                big -= x * value;
            }
            x = (int) Math.floor(decimals / value);
            if (x > 0) {
                int amount = content.get(current);
                content.replace(current, amount + x);
                decimals -= x * value;
            }
        }
    }

    private void removeFromRegister(Map<Money, Integer> map) {
        for (Map.Entry<Money, Integer> pair : map.entrySet()) {
            Money key = pair.getKey();
            int value = pair.getValue();
            int amount = content.get(key);

            content.replace(key, amount - value);
        }
    }

    public Money processEndOfDay() {
        Money contentTotal = countRegister();
        Money profit = contentTotal.subtract(total);
        total = contentTotal;
        return profit;
    }

    private Money countRegister() {
        Money total = new Money(0);
        for (double value : values) {
            total = total.add(new Money(value * content.get(new Money(value))));
        }
        return total;
    }
}
