package stspz.vntu.com.pos.fragments.cashier;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.fragments.BaseDialogFragment;

public class ChangeDialogFragment extends BaseDialogFragment {

    public static final String CHANGE = "change";

    @Bind(R.id.sell_ok)
    AppCompatButton actionOk;
    @Bind(R.id.tv_change)
    TextView tvChange;

    private float change;

    protected int getFragmentLayout() {
        return R.layout.dialog_sell;
    }

    public static ChangeDialogFragment newInstance(float change) {
        ChangeDialogFragment fragment = new ChangeDialogFragment();
        Bundle args = new Bundle();
        args.putFloat(CHANGE, change);
        fragment.setArguments(args);
        return fragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Bundle args = getArguments();
        if (args != null) {
            change = args.getFloat(CHANGE, 0.0f);
            tvChange.setText(String.format(getResources().getString(R.string.change_value), change));
        }
    }

    @OnClick(R.id.sell_ok)
    public void ok() {
        dismiss();
    }

}
