package stspz.vntu.com.pos.rest.responses;

import com.google.gson.annotations.Expose;

import stspz.vntu.com.pos.rest.models.UserData;

/**
 * Created by Alexander on 11.03.2016.
 */

public class GetUserInfoResponse extends BaseResponse {

    @Expose
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
