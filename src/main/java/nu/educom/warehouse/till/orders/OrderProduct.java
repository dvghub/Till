package nu.educom.warehouse.till.orders;

public class OrderProduct implements IOrderProduct {
    private int id;
    private int order_id;
    private int product_id;
    private int amount;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getOrder_id() {
        return order_id;
    }

    @Override
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public int getProduct_id() {
        return product_id;
    }

    @Override
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
