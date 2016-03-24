package stspz.vntu.com.pos.fragments.cashier;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.fragments.BaseDialogFragment;
import stspz.vntu.com.pos.utils.KeyboardUtils;

public class PayOrderDialogFragment extends BaseDialogFragment {

    public static final String TOTAL_MONEY = "total_money";

    @Bind(R.id.action_pay)
    AppCompatButton actionPay;
    @Bind(R.id.ll_progress)
    LinearLayout llProgress;
    @Bind(R.id.et_input)
    AppCompatEditText etInput;
    @Bind(R.id.til_input)
    TextInputLayout tilInput;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;
    @Bind(R.id.tv_change)
    TextView tvChange;

    private Callback mCallback;
    private OnDismissListener onDismissListener;

    private float totalMoney;

    public interface Callback {
        void onPaymentCompleted(float change);
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_pay_order;
    }

    public static PayOrderDialogFragment newInstance(float totalMoney) {
        PayOrderDialogFragment fragment = new PayOrderDialogFragment();
        Bundle args = new Bundle();
        args.putFloat(TOTAL_MONEY, totalMoney);
        fragment.setArguments(args);
        return fragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Bundle args = getArguments();
        if (args != null) {
            totalMoney = args.getFloat(TOTAL_MONEY, 0.0f);
            tvTotalMoney.setText(String.valueOf(totalMoney));
        }

        etInput.addTextChangedListener(new CustomTextWatcher(etInput));
        KeyboardUtils.showKeyboard(etInput);
    }

    @OnClick(R.id.action_pay)
    public void pay() {
        String cache = etInput.getText().toString();

        if (isValidData(cache)) {
            KeyboardUtils.hideKeyboard(etInput);
//            llProgress.setVisibility(View.VISIBLE);
            actionPay.setEnabled(false);
            //this will be called after getting response
            afterLogin(getChange());
        }

    }

    public void afterLogin(float change) {
        if (change > 0) {
            if (mCallback != null) {
                mCallback.onPaymentCompleted(change);
            }
            if (getFragmentManager() != null) {
                dismissAllowingStateLoss();
            }
        } else {
            this.llProgress.setVisibility(View.GONE);
            actionPay.setEnabled(true);
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.onDismissListener != null) {
            this.onDismissListener.onDismiss(dialog);
        }
    }

    private float getChange() {
        String strInput = etInput.getText().toString();
        float change = -1f;
        if (!TextUtils.isEmpty(strInput)) {
            try {
                change = Float.valueOf(strInput) - totalMoney;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return change;
    }

    private boolean isValidData(String cache) {
        boolean result = true;
        if (TextUtils.isEmpty(cache)) {
            result = false;
            tilInput.setError(getString(R.string.payment_empty_input));
        }
        if (result) {
            try {
                float f = Float.valueOf(cache);
                if (f < totalMoney) {
                    tilInput.setError(getString(R.string.payment_not_enough_money));
                    result = false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                tilInput.setError(getString(R.string.payment_incorrect_input));
                result = false;
            }
        }
        return result;
    }

    private class CustomTextWatcher implements TextWatcher {

        private AppCompatEditText mEditText;

        public CustomTextWatcher(AppCompatEditText editText) {
            mEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            ViewParent parent = mEditText.getParent();
            if (parent instanceof TextInputLayout) {
                if (!TextUtils.isEmpty(((TextInputLayout) parent).getError())) {
                    ((TextInputLayout) parent).setError("");
                    ((TextInputLayout) parent).setErrorEnabled(false);
                }
            }
            tvChange.setText(String.valueOf(getChange()));
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

}
