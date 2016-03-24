package stspz.vntu.com.pos.database.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mpodolsky on 02.03.2016.
 */
public class OrderDbItem extends RealmObject {

    @PrimaryKey
    private long code;
    private String name;
    private int count;
    private float price;

    public OrderDbItem() { }

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
}
