package com.sunny.mvvmbilibili.ui.home;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.FragmentHomeBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.BaseFragment;
import com.sunny.mvvmbilibili.ui.example.MainFragment;
import com.sunny.mvvmbilibili.ui.game.GameActivity;
import com.sunny.mvvmbilibili.ui.offline.OfflineActivity;

import javax.inject.Inject;

/**
 * The type Home fragment.
 * Created by Zhou Zejin on 2017/8/16.
 */
public class HomeFragment extends BaseFragment implements HomeMvvmView {

    @Inject HomeViewModel mViewModel;
    @Inject Activity mActivity;
    @Inject @FragmentContext Context mContext;

    private FragmentHomeBinding mBinding;

    private String[] mTitles;
    private Fragment[] mFragments;

    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);

        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        setHasOptionsMenu(true);
        if (mActivity instanceof AppCompatActivity) {
            ((AppCompatActivity) mActivity).setSupportActionBar(mBinding.toolbar);
            ActionBar actionBar = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initViewPager() {
        mTitles = mActivity.getResources().getStringArray(R.array.sections);
        mFragments = new Fragment[mTitles.length];
        for (int i = 0; i < mFragments.length; i++) {
            if (mFragments[i] != null) return;
            switch (i) {
                case 0:
                    mFragments[i] = MainFragment.newInstance();
                    break;
                case 1:
                    mFragments[i] = MainFragment.newInstance();
                    break;
                case 2:
                    mFragments[i] = MainFragment.newInstance();
                    break;
                case 3:
                    mFragments[i] = MainFragment.newInstance();
                    break;
                case 4:
                    mFragments[i] = MainFragment.newInstance();
                    break;
                case 5:
                    mFragments[i] = MainFragment.newInstance();
                    break;
            }
        }

        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        mBinding.viewPager.setOffscreenPageLimit(3);
        mBinding.viewPager.setAdapter(adapter);
        mBinding.viewPager.setCurrentItem(1);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mViewModel.detachView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_home, menu);
        // 设置SearchView的MenuItem
        MenuItem item = menu.findItem(R.id.action_search);
        mBinding.searchView.setMenuItem(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_game:
                startActivity(GameActivity.getStartIntent(mContext));
                break;
            case R.id.action_cache:
                startActivity(OfflineActivity.getStartIntent(mContext));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final class HomePagerAdapter extends FragmentStatePagerAdapter {

        private HomePagerAdapter(FragmentManager fm) {
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
    public boolean isSearchViewOpen() {
        return mBinding.searchView.isSearchOpen();
    }

    @Override
    public void closeSearchView() {
        mBinding.searchView.closeSearch();
    }

    @Override
    public void toggleDrawerLayout() {
        if (mActivity instanceof HomeActivity) {
            ((HomeActivity) mActivity).toggleDrawer();
        }
    }

}
