package stspz.vntu.com.pos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.controller.AccountController;
import stspz.vntu.com.pos.fragments.admin.AdminMainFragment;
import stspz.vntu.com.pos.fragments.cashier.ScannerFragment;
import stspz.vntu.com.pos.fragments.provisor.ProvisorMainFragment;
import stspz.vntu.com.pos.fragments.provisor.ProvisorScannerAddFragment;
import stspz.vntu.com.pos.rest.client.RestClient;
import stspz.vntu.com.pos.rest.models.UserData;
import stspz.vntu.com.pos.rest.requests.BaseRequest;
import stspz.vntu.com.pos.rest.responses.BaseResponse;
import stspz.vntu.com.pos.rest.responses.GetUserInfoResponse;

import static butterknife.ButterKnife.findById;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

//    @Bind(R.id.iv_account)
//    ImageView ivAccount;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    TextView tvAccountType;
    TextView tvAccountName;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View headerView = navigationView.getHeaderView(0);
        tvAccountType = findById(headerView, R.id.tv_account_type);
        tvAccountName = findById(headerView, R.id.tv_account_name);

        Call<GetUserInfoResponse> call = RestClient.getApiService().getUserInfo(new BaseRequest());
        call.enqueue(new Callback<GetUserInfoResponse>() {

            @Override
            public void onResponse(Call<GetUserInfoResponse> call, Response<GetUserInfoResponse> response) {
                UserData account = response.body().getData();

                AccountController accountController = new AccountController(MainActivity.this, account);
                accountController.setupHeader(tvAccountType, tvAccountName);
                accountController.setupNavigation(navigationView);

                navigationView.setNavigationItemSelectedListener(MainActivity.this);
                setupMainView(account);
            }

            @Override
            public void onFailure(Call<GetUserInfoResponse> call, Throwable t) {

            }

        });

    }

    private void setupMainView(UserData account) {

        switch (account.getType()) {
            case UserData.ADMIN: {
                setTitle(getString(R.string.usertype_admin));
                openFragment(AdminMainFragment.newInstance());
                break;
            }
            case UserData.PROVISOR: {
                setTitle("Недавно додані товари");
                openFragment(ProvisorMainFragment.newInstance());
                break;
            }
            case UserData.CASHIER: {
                setTitle(getString(R.string.usertype_cashier));
                openFragment(ScannerFragment.newInstance());
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Specific actions for ADMIN
        if (id == R.id.nav_slideshow) {
//            setTitleRes(R.string.main_titles_scanner);
//            openFragment(ScannerFragment.newInstance());
        } else if (id == R.id.nav_manage) {
//            setTitleRes(R.string.main_titles_scanner);
//            openFragment(ScannerFragment.newInstance());
        }

        // Specific actions for PROVISOR
        else if (id == R.id.nav_provisor_add) {
            setTitle("Додати продукт");
            openFragment(ProvisorScannerAddFragment.newInstance());
        } else if (id == R.id.nav_provisor_edit) {
            setTitle("Редагувати продукт");
            openFragment(ProvisorScannerAddFragment.newInstance());
        } else if (id == R.id.nav_provisor_remove) {
            setTitle("Видалити продукт");
            openFragment(ProvisorScannerAddFragment.newInstance());
        } else if (id == R.id.nav_provisor_view) {
            setTitle("Переглянути продукт");
            openFragment(ProvisorScannerAddFragment.newInstance());
        }

        // Specific actions for CASHIER
        else if (id == R.id.nav_cashier_scanner) {
            setTitleRes(R.string.main_titles_scanner);
            openFragment(ScannerFragment.newInstance());
        } else if (id == R.id.nav_cashier_statistic) {
//            setTitleRes(R.string.main_titles_scanner);
//            openFragment(PaymentFragment.newInstance());
        } else if (id == R.id.nav_logout) {
            logOut();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        Call<BaseResponse> call = RestClient.getApiService().logOut(new BaseRequest());
        call.enqueue(new Callback<BaseResponse>() {

            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Prefs.clear();
                startActivity(new Intent(MainActivity.this, AuthActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }

        });
    }

    private void openFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, tag)
                    .commit();
        }
    }

}
