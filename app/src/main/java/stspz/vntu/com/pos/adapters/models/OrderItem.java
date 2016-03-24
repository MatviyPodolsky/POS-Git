package stspz.vntu.com.pos.adapters.models;

import stspz.vntu.com.pos.database.models.OrderDbItem;

/**
 * Created by mpodolsky on 03.03.2016.
 */
public class OrderItem {

    private long code;
    private String name;
    private int count;
    private float price;

    public OrderItem() { }

    public static OrderItem create(OrderDbItem dbItem) {
        OrderItem item = new OrderItem();
        item.setName(dbItem.getName());
        item.setCode(dbItem.getCode());
        item.setCount(dbItem.getCount());
        item.setPrice(dbItem.getPrice());
        return item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void increaseCount(int value) {
        this.count += value;
    }

    public void increasePrice(float value) {
        this.price += value;
    }

}
