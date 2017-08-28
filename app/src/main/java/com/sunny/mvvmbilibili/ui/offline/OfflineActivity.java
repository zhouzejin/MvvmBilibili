package com.sunny.mvvmbilibili.ui.offline;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityOfflineBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

import javax.inject.Inject;

/**
 * The type Offline activity.
 * Created by Zhou Zejin on 2017/8/25.
 */
public class OfflineActivity extends BaseActivity implements MvvmView {

    @Inject OfflineViewModel mViewModel;

    private ActivityOfflineBinding mBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, OfflineActivity.class);
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_offline);
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onDestroy() {
        mViewModel.detachView();

        super.onDestroy();
    }

}
