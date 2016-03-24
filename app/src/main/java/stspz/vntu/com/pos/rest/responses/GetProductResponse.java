package stspz.vntu.com.pos.rest.responses;

import com.google.gson.annotations.Expose;

import stspz.vntu.com.pos.rest.models.ProductData;

/**
 * Created by Alexander on 11.03.2016.
 */

public class GetProductResponse extends BaseResponse {

    @Expose
    private ProductData data;

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }
}
