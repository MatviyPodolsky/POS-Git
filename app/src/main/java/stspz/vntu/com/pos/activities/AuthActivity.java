package stspz.vntu.com.pos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.pixplicity.easyprefs.library.Prefs;

import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.fragments.auth.LoginFragment;
import stspz.vntu.com.pos.utils.PrefsKeys;

/**
 * Created by Alexander on 01.03.2016.
 */

public class AuthActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_auth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("POS");

        String auth = Prefs.getString(PrefsKeys.ACCESS_TOKEN, null);
        if (TextUtils.isEmpty(auth)) {
            addFragment(LoginFragment.newInstance(), R.id.fragments_container);
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

}
