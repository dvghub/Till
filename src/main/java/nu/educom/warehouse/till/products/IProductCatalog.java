package nu.educom.warehouse.till.products;

import java.util.List;

public interface IProductCatalog
{
    /// <summary>
    /// Find a product for a barcode
    /// </summary>
    /// <returns>the product or <c>null</c> if not found</returns>
    IProduct findProductForBarcode(String barcode);

    /// <returns>a list of all products</returns>
    List<IProduct> getAllProducts();
}