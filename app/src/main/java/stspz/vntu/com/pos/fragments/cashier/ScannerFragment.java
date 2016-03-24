package stspz.vntu.com.pos.fragments.cashier;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alertdialogpro.AlertDialogPro;
import com.google.zxing.Result;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.adapters.ScannerAdapter;
import stspz.vntu.com.pos.adapters.models.OrderItem;
import stspz.vntu.com.pos.adapters.utils.SimpleItemTouchHelperCallback;
import stspz.vntu.com.pos.fragments.BaseFragment;
import stspz.vntu.com.pos.rest.client.RestClient;
import stspz.vntu.com.pos.rest.requests.BaseRequest;
import stspz.vntu.com.pos.rest.responses.GetProductResponse;
import stspz.vntu.com.pos.utils.DividerItemDecoration;
import stspz.vntu.com.pos.utils.KeyboardUtils;

/**
 * Created by Alexander on 01.03.2016.
 */

public class ScannerFragment extends BaseFragment implements ZXingScannerView.ResultHandler {

    public static final int SCANNER_DELAY = 1000;

    @Bind(R.id.scanner)
    ZXingScannerView scannerView;
    @Bind(R.id.edt_barcode)
    EditText edtBarcode;
    @Bind(R.id.recycler_scanner)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private ScannerAdapter scannerAdapter;

    private ItemTouchHelper mItemTouchHelper;
    private float currentChange = -1f;

    @Override
    public int getLayoutResource() {
        scannerView = new ZXingScannerView(getActivity());
        return R.layout.fragment_scanner;
    }

    public static Fragment newInstance() {
        Fragment fragment = new ScannerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecycler();

//        OrderDbItem item = new OrderDbItem();
//        item.setCode(4820049490107l);
//        item.setCount(1);
//        item.setName("Зошит з інгліша");
//        item.setPrice(53.7f);
//        DatabaseHelper.addOrderItem(getActivity(), item);
//        OrderDbItem item1 = new OrderDbItem();
//        item1.setCode(4820006472184l);
//        item1.setCount(1);
//        item1.setName("Зошит з лекцій");
//        item1.setPrice(112.7f);
//        DatabaseHelper.addOrderItem(getActivity(), item1);
//        OrderDbItem item2 = new OrderDbItem();
//        item2.setCode(4044572202468l);
//        item2.setCount(1);
//        item2.setName("Санін зошит");
//        item2.setPrice(6.0f);
//        DatabaseHelper.addOrderItem(getActivity(), item2);
//        OrderDbItem item3 = new OrderDbItem();
//        item3.setCode(10783673l);
//        item3.setCount(1);
//        item3.setName("Санін студак");
//        item3.setPrice(1428.0f);
//        DatabaseHelper.addOrderItem(getActivity(), item3);
//        DatabaseHelper.clearOrderItems(getActivity());
    }

    @OnClick(R.id.btn_approve_barcode)
    public void approveBarcode() {
        String barcode = edtBarcode.getText().toString();
        if (!TextUtils.isEmpty(barcode)) {
            getProductInfo(barcode);
        }
    }

    private void getProductInfo(String barcode) {
        Call<GetProductResponse> call = RestClient.getApiService().getProductByCode(barcode, new BaseRequest());
        call.enqueue(new Callback<GetProductResponse>() {

            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                if (response.body().isSuccess()) {
                    OrderItem item = new OrderItem();
                    item.setName(response.body().getData().getName());
                    item.setPrice(response.body().getData().getPurchasedAmount());
                    item.setCount(response.body().getData().getCount()); //TODO wtf ???
                    item.setCode(response.body().getData().getCode());
                    scannerAdapter.addItem(item);
                    recyclerView.scrollToPosition(0);
                }
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {

            }

        });
    }

    @OnClick(R.id.fab_to_pay)
    public void toPay() {
        if (scannerAdapter != null && scannerAdapter.getItemCount() > 0) {
            final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            final PayOrderDialogFragment payDialog = PayOrderDialogFragment.newInstance(scannerAdapter.getTotalMoney());
            payDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    KeyboardUtils.hideKeyboard(getActivity());
                    if (currentChange >= 0) {
                        final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                        ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(currentChange);
                        changeDialogFragment.setCancelable(false);
                        changeDialogFragment.show(ft1, "change_dialog");
                        scannerAdapter.clear();
                    }
                    currentChange = -1f;
                }
            });
            payDialog.setCallback(new PayOrderDialogFragment.Callback() {
                @Override
                public void onPaymentCompleted(float change) {
                    currentChange = change;
                }
            });
            payDialog.show(ft, "pay_order_dialog");
        }
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
    public void handleResult(Result rawResult) {

        String barcode = rawResult.getText();
        if (!TextUtils.isEmpty(barcode)) {
            getProductInfo(barcode);
        }

//        try {
//            final OrderDbItem item = DatabaseHelper.getOrderItemByCode(getActivity(), Long.valueOf(rawResult.getText()));
//            if (item != null) {
//                scannerAdapter.addItem(OrderItem.create(item));
//                recyclerView.scrollToPosition(0);
//            } else {
//                OrderItem item1 = new OrderItem();
//                item1.setName(rawResult.getText());
//                item1.setPrice(12.3f);
//                item1.setCount(12);
//                item1.setCode(Long.valueOf(rawResult.getText()));
//                scannerAdapter.addItem(item1);
//                recyclerView.scrollToPosition(0);
////                Toast.makeText(getActivity(), "No such item in database.", Toast.LENGTH_SHORT).show();
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            Toast.makeText(getActivity(), "Invalid bar code.", Toast.LENGTH_SHORT).show();
//        }

        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        resumeScanner();
    }

    private void resumeScanner () {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scannerView.resumeCameraPreview(ScannerFragment.this);
            }
        }, SCANNER_DELAY);
    }

    private void initRecycler() {
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        scannerAdapter = new ScannerAdapter(getActivity(), new ArrayList<OrderItem>());
        scannerAdapter.setCallback(new ScannerAdapter.Callback() {
            @Override
            public void onItemClick(int position) {
                //TODO show info
            }

            @Override
            public void onItemDismiss(final OrderItem item) {
                AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getActivity());
                builder.setIcon(getResources().getDrawable(R.mipmap.ic_launcher)).
                        setTitle(item.getName()).
                        setMessage(getString(R.string.are_u_sure_u_want_delete)).
                        setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                scannerAdapter.remove(item);
                                dialog.dismiss();
                            }
                        }).
                        setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                scannerAdapter.notifyItemChanged(scannerAdapter.getItemPosition(item));
                                dialog.dismiss();
                            }
                        }).
                        show();
            }
        });
        recyclerView.setAdapter(scannerAdapter);

        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.endAnimations();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper.Callback tcallback =
                new SimpleItemTouchHelperCallback(scannerAdapter);
        mItemTouchHelper = new ItemTouchHelper(tcallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

    }

}
