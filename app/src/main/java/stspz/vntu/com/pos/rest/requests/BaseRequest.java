package stspz.vntu.com.pos.rest.requests;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.pixplicity.easyprefs.library.Prefs;

import stspz.vntu.com.pos.utils.PrefsKeys;

/**
 * Created by Alexander on 29.02.2016.
 */

public class BaseRequest {

    @Expose
    private String auth;

    public BaseRequest() {
        String auth = Prefs.getString(PrefsKeys.ACCESS_TOKEN, null);
        if (!TextUtils.isEmpty(auth)) {
            this.auth = auth;
        }
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
