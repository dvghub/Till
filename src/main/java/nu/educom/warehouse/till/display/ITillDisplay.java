package nu.educom.warehouse.till.display;

import nu.educom.warehouse.till.products.IProduct;

import java.util.List;

/**
 * Interface that every object that wants to display something for the till needs to implement
 */
public interface ITillDisplay
{
    /**
     * Display all products
     * @param title the title of the list
     * @param products a list of products to show
     */
    void displayProducts(String title, List<IProduct> products);

    /// <summary>
    /// Display the lines of the client display
    /// </summary>
    /// <param name="line1">first line (max 40 characters)</param>
    /// <param name="line2">second line (max 40 characters)</param>
    void displayClientScreen(String line1, String line2);
}

