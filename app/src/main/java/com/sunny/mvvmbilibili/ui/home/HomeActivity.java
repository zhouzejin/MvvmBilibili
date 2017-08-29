package com.sunny.mvvmbilibili.ui.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.SyncService;
import com.sunny.mvvmbilibili.databinding.ActivityHomeBinding;
import com.sunny.mvvmbilibili.ui.attention.AttentionFragment;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.favourite.FavouriteFragment;
import com.sunny.mvvmbilibili.ui.history.HistoryFragment;
import com.sunny.mvvmbilibili.ui.offline.OfflineActivity;
import com.sunny.mvvmbilibili.ui.wallet.WalletFragment;
import com.sunny.mvvmbilibili.utils.ActivityUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Home activity.
 * Created by Zhou Zejin on 2017/8/14.
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String HOME_TAG = "home_fragment";
    private static final String FAVOURITE_TAG = "favourite_fragment";
    private static final String HISTORY_TAG = "history_fragment";
    private static final String ATTENTION_TAG = "attention_fragment";
    private static final String WALLET_TAG = "wallet_fragment";

    private ActivityHomeBinding mBinding;

    private Map<String, Fragment> mFragments;
    private String mCurrentTag;
    private HomeMvvmView mHomeMvvmView;

    private long mExitTime;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(SyncService.getStartIntent(this));
    }

    @Override
    public void initComponent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setContentView(mBinding.getRoot());
        mBinding.navigationView.setNavigationItemSelectedListener(this);

        addFragments();
        // 显示默认页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragments.get(HOME_TAG)).commit();
        mCurrentTag = HOME_TAG;
    }

    private void addFragments() {
        mFragments = new HashMap<>();
        HomeFragment homeFragment = HomeFragment.newInstance();
        mHomeMvvmView = homeFragment;

        mFragments.put(HOME_TAG, homeFragment);
        mFragments.put(FAVOURITE_TAG, FavouriteFragment.newInstance());
        mFragments.put(HISTORY_TAG, HistoryFragment.newInstance());
        mFragments.put(ATTENTION_TAG, AttentionFragment.newInstance());
        mFragments.put(WALLET_TAG, WalletFragment.newInstance());
    }

    private void showFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment hideFragment = mFragments.get(mCurrentTag);
        Fragment showFragment = mFragments.get(tag);
        if (!showFragment.isAdded()) {
            ActivityUtil.addFragmentToActivity(fm, showFragment, R.id.container, tag);
        }
        ActivityUtil.hideAndShowFragment(fm, hideFragment, showFragment);
        mCurrentTag = tag;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.action_home:
                showFragment(HOME_TAG);
                return true;
            case R.id.action_vip:
                ToastUtil.showShort(this, "敬请期待");
                break;
            case R.id.action_offline:
                startActivity(OfflineActivity.getStartIntent(this));
                break;
            case R.id.action_favourite:
                showFragment(FAVOURITE_TAG);
                return true;
            case R.id.action_history:
                showFragment(HISTORY_TAG);
                return true;
            case R.id.action_attention:
                showFragment(ATTENTION_TAG);
                return true;
            case R.id.action_wallet:
                showFragment(WALLET_TAG);
                return true;
            case R.id.action_settings:
                ToastUtil.showShort(this, "敬请期待");
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return;
        } else {
            if (mHomeMvvmView.isSearchViewOpen()) {
                mHomeMvvmView.closeSearchView();
                return;
            }
        }
        exitApp();
    }

    private void exitApp() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtil.showShort(this, getString(R.string.back_hint));
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
            // 彻底退出APP
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    public void toggleDrawer() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mBinding.drawerLayout.openDrawer(GravityCompat.START);
        }
    }

}
