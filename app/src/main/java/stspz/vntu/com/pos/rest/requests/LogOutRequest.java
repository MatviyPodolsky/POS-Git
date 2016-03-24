package stspz.vntu.com.pos.rest.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 10.03.2016.
 */

public class LogOutRequest {

    @Expose
    private String auth;

    public LogOutRequest(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
