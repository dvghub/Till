package nu.educom.warehouse.till.products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;

public class ProductCatalog implements IProductCatalog {
    private final List<IProduct> products;

    public ProductCatalog() {
        products = getProducts();
    }

    @Override
    public IProduct findProductForBarcode(String barcode) {
        for (IProduct product : products) {
            if (product.getBarcode().equals(barcode)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<IProduct> getAllProducts() {
        return products;
    }

    private List<IProduct> getProducts() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr)
                .addAnnotatedClass(Product.class)
                .addResource("product.hbm.xml")
                .getMetadataBuilder()
                .build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        TypedQuery query = session.createQuery("from Product");
        List list = query.getResultList();
        Iterator itr = list.iterator();

        List<IProduct> products = new ArrayList<>();

        while (itr.hasNext()) {
            Product product = (Product) itr.next();
            products.add(product);
        }

        session.close();
        return products;
    }
}
