package com.sunny.mvvmbilibili.ui.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivitySearchBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.example.MainFragment;
import com.sunny.mvvmbilibili.utils.ViewUtil;

import javax.inject.Inject;

/**
 * The type Search activity.
 * Created by Zhou Zejin on 2017/9/27.
 */
public class SearchActivity extends BaseActivity implements SearchMvvmView {

    private static final String EXTRA_QUERY_STRING =
            "com.sunny.mvvmbilibili.ui.search.SearchActivity.EXTRA_QUERY_STRING";

    @Inject SearchViewModel mViewModel;

    private ActivitySearchBinding mBinding;

    private String[] mTitles;
    private Fragment[] mFragments;

    public static Intent getStartIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_QUERY_STRING, query);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.attachView(this);
        mViewModel.query(getIntent().getStringExtra(EXTRA_QUERY_STRING));
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
        initView();
        initViewPager();
    }

    private void initView() {
        mViewModel.searchLayout.searchStr.set(getIntent().getStringExtra(EXTRA_QUERY_STRING));
    }

    private void initViewPager() {
        mTitles = new String[]{"综合", "番剧(3)", "用户(99+)", "影视(99+)"};
        mFragments = new Fragment[]{
                MainFragment.newInstance(),
                MainFragment.newInstance(),
                MainFragment.newInstance(),
                MainFragment.newInstance(),
        };

        SearchPagerAdapter adapter = new SearchPagerAdapter(getSupportFragmentManager());
        mBinding.viewPager.setOffscreenPageLimit(mFragments.length - 1);
        mBinding.viewPager.setAdapter(adapter);
        mBinding.viewPager.setCurrentItem(0);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.tabLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewUtil.setIndicatorWidth(mBinding.tabLayout, 24);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mViewModel.detachView();
        super.onDestroy();
    }

    private final class SearchPagerAdapter extends FragmentStatePagerAdapter {

        private SearchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void backView() {
        onBackPressed();
    }

}
