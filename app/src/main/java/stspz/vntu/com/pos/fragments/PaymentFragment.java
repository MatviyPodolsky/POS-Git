package stspz.vntu.com.pos.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.utils.DialogUtils;

/**
 * Created by Alexander on 02.03.2016.
 */

public class PaymentFragment extends BaseFragment {

    public static final String TOTAL_MONEY = "total_money";

    @Bind(R.id.tv_sum)
    TextView tvSum;
    @Bind(R.id.edt_input)
    EditText edtInput;
    @Bind(R.id.short_change)
    TextView tvChange;

    private float totalMoney;

    public static Fragment newInstance(float totalMoney) {
        Fragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putFloat(TOTAL_MONEY, totalMoney);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_payment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            totalMoney = args.getFloat(TOTAL_MONEY, 0.0f);
        }

        tvSum.setText(String.valueOf(totalMoney));

    }

    @OnClick (R.id.fab)
    void sell() {
        DialogUtils.showDialog(getActivity(), "Оплачено", "ОК", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // TODO Return to scanner with empty list
            }
        });
    }

}
