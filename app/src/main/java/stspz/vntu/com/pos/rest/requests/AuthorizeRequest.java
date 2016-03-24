package stspz.vntu.com.pos.rest.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 09.03.2016.
 */
public class AuthorizeRequest extends BaseRequest{

    @Expose
    private String nickname;
    @Expose
    private String password;

    public AuthorizeRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
