package stspz.vntu.com.pos.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import stspz.vntu.com.pos.R;

/**
 * Created by Alexander on 04.03.2016.
 */
public class SellDialog extends DialogFragment {

    public static final int RESOURCE = R.layout.dialog_sell;

    private Callback callback;

    public static SellDialog newInstance(String message, String btnText, DialogInterface.OnClickListener callback) {
        SellDialog fragment = new SellDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(RESOURCE, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        ButterKnife.bind(this, view);


        return builder.create();
    }

    @OnClick(R.id.sell_ok)
    void sellOk() {
        if (callback != null) {
            callback.onOkClick();
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onOkClick();
    }

}
