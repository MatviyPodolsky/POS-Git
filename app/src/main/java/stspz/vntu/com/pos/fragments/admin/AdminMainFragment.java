package stspz.vntu.com.pos.fragments.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.fragments.BaseFragment;

/**
 * Created by Alexander on 07.03.2016.
 */

public class AdminMainFragment extends BaseFragment {

    public static Fragment newInstance() {
        Fragment fragment = new AdminMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_admin_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
