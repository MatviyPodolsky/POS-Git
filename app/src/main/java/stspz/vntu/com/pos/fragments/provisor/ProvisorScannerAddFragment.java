package stspz.vntu.com.pos.fragments.provisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.zxing.Result;

import butterknife.Bind;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.activities.MainActivity;
import stspz.vntu.com.pos.fragments.BaseFragment;

/**
 * Created by Alexander on 07.03.2016.
 */
public class ProvisorScannerAddFragment extends BaseFragment implements ZXingScannerView.ResultHandler {

    @Bind(R.id.scanner_provisor)
    ZXingScannerView scannerView;
    @Bind(R.id.edt_barcode)
    EditText edtBarcode;

    public static Fragment newInstance() {
        Fragment fragment = new ProvisorScannerAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        scannerView = new ZXingScannerView(getActivity());
        return R.layout.provisor_scanner_add_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.fab_to_next_add)
    void addNew() {
        String barcode = edtBarcode.getText().toString();
        if (!TextUtils.isEmpty(barcode)) {
            ((MainActivity) getActivity()).addFragment(
                    ProvisorAddProductFragment.newInstance(barcode), R.id.fragment_container, ProvisorAddProductFragment.class.getSimpleName());
        }
    }

    @Override
    public void handleResult(Result result) {
        String barcode = result.getText();
        edtBarcode.setText(barcode);
        edtBarcode.setSelection(barcode.length());
    }
}
