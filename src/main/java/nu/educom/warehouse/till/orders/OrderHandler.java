package nu.educom.warehouse.till.orders;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.products.IProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Map;

public class OrderHandler implements IOrderHandler {
    public OrderHandler() {}

    @Override
    public int insertOrder(Money total) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr)
                .addResource("order.hbm.xml")
                .getMetadataBuilder()
                .build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        double double_total = total.divide(new Money(1));

        Order order = new Order();
        order.setTotal(double_total);

        int order_id = (int) session.save(order);
        t.commit();
        factory.close();
        session.close();

        return order_id;
    }

    @Override
    public void insertOrderProducts(int order_id, Map<IProduct, Integer> purchases) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr)
                .addResource("order_product.hbm.xml")
                .getMetadataBuilder()
                .build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        for (Map.Entry<IProduct, Integer> pair : purchases.entrySet()) {
            IOrderProduct product = new OrderProduct();
            product.setOrder_id(order_id);
            product.setProduct_id(pair.getKey().getId());
            product.setAmount(pair.getValue());

            session.save(product);
        }

        t.commit();
        factory.close();
        session.close();
    }
}
