package stspz.vntu.com.pos.rest.requests;

import com.google.gson.annotations.Expose;

import stspz.vntu.com.pos.rest.models.ProductData;

/**
 * Created by Alexander on 11.03.2016.
 */

public class CreateProductRequest extends BaseRequest {

    @Expose
    private ProductData data;

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }

}
