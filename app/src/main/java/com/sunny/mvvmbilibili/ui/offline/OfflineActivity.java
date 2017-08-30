package com.sunny.mvvmbilibili.ui.offline;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityOfflineBinding;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

/**
 * The type Offline activity.
 * Created by Zhou Zejin on 2017/8/25.
 */
public class OfflineActivity extends BaseActivity implements OfflineMvvmView {

    @Inject @ActivityContext Context mContext;
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
        mViewModel.isShowContentEmpty.set(true);
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_offline);
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onDestroy() {
        mViewModel.detachView();

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_offline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_offline_setting) {
            ToastUtil.showShort(mContext, getString(R.string.action_offline_setting));
        }
        return super.onOptionsItemSelected(item);
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void backView() {
        onBackPressed();
    }

}
