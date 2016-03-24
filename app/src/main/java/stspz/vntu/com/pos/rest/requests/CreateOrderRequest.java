package stspz.vntu.com.pos.rest.requests;

import com.google.gson.annotations.Expose;

import stspz.vntu.com.pos.rest.models.OrderData;

/**
 * Created by Alexander on 11.03.2016.
 */

public class CreateOrderRequest extends BaseRequest {

    @Expose
    private OrderData data;

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }
}
