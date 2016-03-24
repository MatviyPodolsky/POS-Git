package stspz.vntu.com.pos.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 11.03.2016.
 */
public class OrderItem {

    @Expose
    private int productId;
    @Expose
    private int count;
    @Expose
    private int amount;

    public OrderItem(int productId, int count, int amount) {
        this.productId = productId;
        this.count = count;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
