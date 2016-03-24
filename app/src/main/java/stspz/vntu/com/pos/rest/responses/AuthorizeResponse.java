package stspz.vntu.com.pos.rest.responses;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 10.03.2016.
 */

public class AuthorizeResponse extends BaseResponse {

    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
