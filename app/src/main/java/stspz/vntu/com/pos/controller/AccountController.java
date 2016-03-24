package stspz.vntu.com.pos.controller;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.widget.TextView;

import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.rest.models.UserData;

/**
 * Created by Alexander on 29.02.2016.
 */

public class AccountController {

    private UserData account;
    private Context context;

    public AccountController(Context context, UserData account) {
        this.account = account;
        this.context = context;
    }

    public void setupHeader(TextView tvAccountType, TextView tvAccountName) {
        tvAccountType.setText(getTypeName(account.getType()));
        tvAccountName.setText(getFullName());
    }

    private String getFullName() {
        StringBuilder builder = new StringBuilder();
        builder.append(account.getFirstName());
        builder.append(" ");
        builder.append(account.getLastName());
        return builder.toString();
    }

    private String getTypeName(int type) {
        String name = "NOT_AUTHORIZED";
        switch (type){
            case UserData.CASHIER: {
                name = context.getString(R.string.usertype_cashier);
                break;
            }
            case UserData.PROVISOR: {
                name = context.getString(R.string.usertype_provizor);
                break;
            }
            case UserData.ADMIN: {
                name = context.getString(R.string.usertype_admin);
                break;
            }
            default:
                break;
        }
        return name;
    }

    public void setupNavigation(NavigationView navigationView) {
        int accountType = account.getType();

        navigationView.getMenu().clear();
        switch (accountType) {
            case UserData.ADMIN : {
                navigationView.inflateMenu(R.menu.menu_admin);
                break;
            }
            case UserData.PROVISOR : {
                navigationView.inflateMenu(R.menu.menu_provisor);
                break;
            }
            case UserData.CASHIER : {
                navigationView.inflateMenu(R.menu.menu_cashier);
                break;
            }
            default :
                break;
        }
    }

}
