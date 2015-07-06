package entity;

public class Goods {

    private int goods_id;
    private String item_name;
    private String item_description;
    private int item_price;
    private int delete_status;

    public Goods(String item_name, String item_description, int item_price, int delete_status) {
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_price = item_price;
        this.delete_status = delete_status;
    }

    public Goods(String item_name, String item_description, int item_price) {
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_price = item_price;
    }

    public Goods(String item_name, String item_description) {
        this.item_name = item_name;
        this.item_description = item_description;
    }

    public Goods(int goods_id, String item_name, String item_description, int item_price) {
        this.goods_id = goods_id;
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_price = item_price;
    }
    
    
    public Goods() {
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(int delete_status) {
        this.delete_status = delete_status;
    }

    @Override
    public String toString() {
        return "Goods{" + "goods_id=" + goods_id + ", name=" + item_name + ","
                + ", price=" + item_price
                + ", description=" + item_description + ", delete_status="
                + delete_status + '}';
    }


}
