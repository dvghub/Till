package nu.educom.warehouse.till.till;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.display.ITillDisplay;

import java.util.Map;

public interface ITill {

    /**
     * Installs an interface to be used when displaying
     * @param tillDisplay The interface to use from now on
     */
    void setDisplayInterface(ITillDisplay tillDisplay);

    /**
     * Handle a scan of a barcode
     * @param barcode The scanned barcode
     * @return <code>true</code> if successful, <code>false</code> otherwise
     */
    boolean handleBarcode(String barcode);


    /**
     * Initiate a payment
     * @param amount The amount paid
     * @return the change to return or null on failure
     */
    Map<Money, Integer> initiatePayment(Money amount);

    /**
     * Show all products
     */
    void showAllProducts();

}
