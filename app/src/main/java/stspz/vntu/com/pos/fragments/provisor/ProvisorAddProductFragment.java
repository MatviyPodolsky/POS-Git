package stspz.vntu.com.pos.fragments.provisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.fragments.BaseFragment;
import stspz.vntu.com.pos.rest.client.RestClient;
import stspz.vntu.com.pos.rest.models.ProductData;
import stspz.vntu.com.pos.rest.requests.CreateProductRequest;
import stspz.vntu.com.pos.rest.responses.BaseResponse;

/**
 * Created by Alexander on 07.03.2016.
 */

public class ProvisorAddProductFragment extends BaseFragment {

    public static final String ARG_BARCODE = "ARG_BARCODE";

    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.edt_category)
    EditText edtCategory;
    @Bind(R.id.edt_name)
    EditText edtName;
    @Bind(R.id.edt_created)
    EditText edtCreated;
    @Bind(R.id.edt_date_to)
    EditText edtDateTo;
    @Bind(R.id.edt_price)
    EditText edtPrice;
    @Bind(R.id.edt_count)
    EditText edtCount;

    private String barcode;

    public static Fragment newInstance(String barcode) {
        Fragment fragment = new ProvisorAddProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BARCODE, barcode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.provisor_add_product_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barcode = getArguments().getString(ARG_BARCODE);
    }

    @OnClick(R.id.fab_complete_add)
    void addNew() {
        if (isValidFields()) {
            sendRequest();
        } else {
            Toast.makeText(getActivity(), "Please try to add valid product information", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidFields() {
        if (TextUtils.isEmpty(edtCreated.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(edtDateTo.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(edtName.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(edtCount.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(edtPrice.getText().toString())) {
            return false;
        }
        return true;
    }

    private void sendRequest() {
        Call<BaseResponse> call1 = RestClient.getApiService().createProduct(createRequest());
        call1.enqueue(new Callback<BaseResponse>() {

            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()) {
                    // TODO return to main provisor fragment
                    Toast.makeText(getActivity(), "Your product added successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private CreateProductRequest createRequest() {
        CreateProductRequest request = new CreateProductRequest();
        ProductData productData = new ProductData();
        productData.setCreated(edtCreated.getText().toString());
        productData.setExpires(edtDateTo.getText().toString());
        productData.setName(edtName.getText().toString());
        productData.setCount(Integer.valueOf(edtCount.getText().toString()));
        productData.setCode(Integer.valueOf(barcode));
        productData.setPurchasedAmount(Float.parseFloat(edtPrice.getText().toString()));
        productData.setForSale(true);
        request.setData(productData);
        return request;
    }

}