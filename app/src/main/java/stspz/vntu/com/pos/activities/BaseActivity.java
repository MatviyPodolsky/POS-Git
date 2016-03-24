package stspz.vntu.com.pos.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import stspz.vntu.com.pos.R;

/**
 * Created by Alexander on 29.02.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(false);

                String title = null;
                if (savedInstanceState != null) {
                    title = savedInstanceState.getString("title");
                }

                supportActionBar.setTitle(title);
            }
        }
    }

    protected abstract int getLayoutResource();

    public void setTitleRes(int title) {
        if (getSupportActionBar() != null) {
            setTitle(getString(title));
        }
    }

    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void addFragment(Fragment fragment, int containerId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
                .add(containerId, fragment)
                .commit();
    }

    public void addFragment(Fragment fragment, int containerId, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
                .add(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

}
