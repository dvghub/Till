package nu.educom.warehouse.till.orders;

interface IOrder {
    void setId(int id);

    int getId();

    void setTotal(double total);

    double getTotal();
}
