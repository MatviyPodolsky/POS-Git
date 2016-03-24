package stspz.vntu.com.pos.rest.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 11.03.2016.
 */

public class OrderData {

    @Expose
    private int amount;
    @Expose
    private List<OrderItem> items = new ArrayList<>();

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
