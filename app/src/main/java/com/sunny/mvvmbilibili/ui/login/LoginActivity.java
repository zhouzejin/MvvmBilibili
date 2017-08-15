package com.sunny.mvvmbilibili.ui.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityLoginBinding;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.home.HomeActivity;

import javax.inject.Inject;

/**
 * The type Login activity.
 * Created by Zhou zejin on 2017/8/4.
 */
public class LoginActivity extends BaseActivity implements LoginMvvmView {

    @Inject @ActivityContext Context mContext;
    @Inject LoginViewModel mViewModel;

    private ActivityLoginBinding mBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.attachView(this);
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onDestroy() {
        mViewModel.detachView();

        super.onDestroy();
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void closeView() {
        finish();
    }

    @Override
    public void goHomeView() {
        startActivity(HomeActivity.getStartIntent(mContext));
    }

}
