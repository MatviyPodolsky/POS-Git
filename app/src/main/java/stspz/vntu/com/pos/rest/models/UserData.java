package stspz.vntu.com.pos.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 11.03.2016.
 */

public class UserData {

    public static final int CASHIER = 0;
    public static final int PROVISOR = 1;
    public static final int ADMIN = 2;

    @Expose
    private String id;
    @Expose
    private int type;
    @Expose
    private String nickname;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
