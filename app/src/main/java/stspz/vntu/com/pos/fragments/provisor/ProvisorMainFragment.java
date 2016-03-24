package stspz.vntu.com.pos.fragments.provisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.activities.MainActivity;
import stspz.vntu.com.pos.fragments.BaseFragment;

/**
 * Created by Alexander on 07.03.2016.
 */

public class ProvisorMainFragment extends BaseFragment {

    @Bind(R.id.recycler_latest_added)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    public static Fragment newInstance() {
        Fragment fragment = new ProvisorMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_provisor_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @OnClick (R.id.fab_to_add)
    void addNew() {
        ((MainActivity) getActivity()).addFragment(
                ProvisorScannerAddFragment.newInstance(), R.id.fragment_container, ProvisorScannerAddFragment.class.getSimpleName());
    }

}
