package stspz.vntu.com.pos.fragments.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.activities.MainActivity;
import stspz.vntu.com.pos.fragments.BaseFragment;
import stspz.vntu.com.pos.rest.client.RestClient;
import stspz.vntu.com.pos.rest.requests.AuthorizeRequest;
import stspz.vntu.com.pos.rest.responses.AuthorizeResponse;
import stspz.vntu.com.pos.utils.PrefsKeys;

/**
 * Created by Alexander on 01.03.2016.
 */

public class LoginFragment extends BaseFragment {

    @Bind(R.id.edt_username)
    EditText edtUserName;
    @Bind(R.id.edt_password)
    EditText edtPassword;

    public static Fragment newInstance() {
        Fragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtUserName.setText("provizor");
        edtPassword.setText("1234");
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String userName = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        edtUserName.setError(null);
        edtPassword.setError(null);

        if (isValidData(userName, password)) {
            AuthorizeRequest request = new AuthorizeRequest(userName, password);
            Call<AuthorizeResponse> call = RestClient.getApiService().authorize(request);
            call.enqueue(new Callback<AuthorizeResponse>() {

                @Override
                public void onResponse(Call<AuthorizeResponse> call, Response<AuthorizeResponse> response) {
                    if (response.body().isSuccess() && response.body().getData() != null) {

                        String accessToken = ((AuthorizeResponse) ((Response) response).body()).getData();
                        Prefs.putString(PrefsKeys.ACCESS_TOKEN, accessToken);

                        startActivity(new Intent(getActivity(), MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    } else {
                        String errorMessage = response.message();
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AuthorizeResponse> call, Throwable t) {

                }

            });

        }
    }

    private boolean isValidData(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            edtUserName.setError(getString(R.string.auth_login_error_username));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.auth_login_error_password));
            return false;
        }
        return true;
    }
}
