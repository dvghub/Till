package nu.educom.warehouse.till.orders;

interface IOrderProduct {
    int getId();

    void setId(int id);

    int getOrder_id();

    void setOrder_id(int order_id);

    int getProduct_id();

    void setProduct_id(int product_id);

    int getAmount();

    void setAmount(int amount);
}
