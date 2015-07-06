package entity;

public class Orders {

    private int orders_id;
    private int users_id;
    private int goods_id;
    private int goods_count;
    private String payment;
    private int delete_status;

    public Orders() {
    }

    public Orders(int orders_id, int users_id, int goods_id, int goods_count, String payment, int delete_status) {
        this.orders_id = orders_id;
        this.users_id = users_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.payment = payment;
        this.delete_status = delete_status;
    }

    public Orders(int orders_id, int users_id, int goods_id, int goods_count, String payment) {
        this.orders_id = orders_id;
        this.users_id = users_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.payment = payment;
    }

    public Orders(int users_id, int goods_id, int goods_count, String payment) {
        this.users_id = users_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.payment = payment;
    }

    public Orders(int orders_id, int goods_count, String payment) {
        this.orders_id = orders_id;
        this.goods_count = goods_count;
        this.payment = payment;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(int delete_status) {
        this.delete_status = delete_status;
    }

}
