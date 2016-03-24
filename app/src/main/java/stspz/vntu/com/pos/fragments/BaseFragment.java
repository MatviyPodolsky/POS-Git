package stspz.vntu.com.pos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Alexander on 29.02.2016.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutResource();

    protected void addNestedFragment(int containerId, Fragment fragment, String tag) {
        FragmentManager fragmentManager;
        if (getParentFragment() != null) { // parent fragment already nested
            fragmentManager = getParentFragment().getChildFragmentManager();
        } else {
            fragmentManager = getChildFragmentManager();
        }
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
                .add(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

}
