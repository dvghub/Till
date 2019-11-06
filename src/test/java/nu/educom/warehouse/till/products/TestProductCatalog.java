package nu.educom.warehouse.till.products;

import java.util.ArrayList;
import java.util.List;


public class TestProductCatalog implements IProductCatalog {

    private final IProduct productToReturn = new TestProduct(1);

    public final List<IProduct> listToReturn = new ArrayList<>();

    public TestProductCatalog() {
        listToReturn.add(new TestProduct(1));
        listToReturn.add(new TestProduct(2));
    }

    @Override
    public IProduct findProductForBarcode(String barcode) {
        return productToReturn;
    }

    @Override
    public List<IProduct> getAllProducts() {
        return listToReturn;
    }
}
