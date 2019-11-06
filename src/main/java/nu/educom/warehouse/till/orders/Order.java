package nu.educom.warehouse.till.orders;

public class Order implements IOrder {
    private int id;
    private double total;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public double getTotal() {
        return total;
    }
}
