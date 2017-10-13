package com.sunny.mvvmbilibili.ui.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.SearchNav;
import com.sunny.mvvmbilibili.databinding.ActivitySearchBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.example.MainFragment;
import com.sunny.mvvmbilibili.ui.layout.SearchLayout;
import com.sunny.mvvmbilibili.ui.search.bangumi.SearchBangumiFragment;
import com.sunny.mvvmbilibili.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Search activity.
 * Created by Zhou Zejin on 2017/9/27.
 */
public class SearchActivity extends BaseActivity {

    public final SearchLayout searchLayout = new SearchLayout() {
        @Override
        public void back() {
            onBackPressed();
        }

        @Override
        public void search() {
            query(searchStr.get());
            ViewUtil.hideKeyboard(SearchActivity.this);
        }
    };

    private static final String EXTRA_QUERY_STRING =
            "com.sunny.mvvmbilibili.ui.search.SearchActivity.EXTRA_QUERY_STRING";

    private ActivitySearchBinding mBinding;
    private String mKeyword;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    public static Intent getStartIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_QUERY_STRING, query);
        return intent;
    }

    @Override
    public void initComponent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mBinding.setActivity(this);
        setContentView(mBinding.getRoot());
        initView();
        query(getIntent().getStringExtra(EXTRA_QUERY_STRING));
    }

    private void initView() {
        searchLayout.searchStr.set(getIntent().getStringExtra(EXTRA_QUERY_STRING));
    }

    private void query(String queryStr) {
        mKeyword = queryStr;

        mFragments.clear();
        mTitles.clear();
        mFragments.add(SearchFragment.newInstance(mKeyword));
        mTitles.add(getString(R.string.archive));

        mBinding.viewPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));
        mBinding.viewPager.setCurrentItem(0);

        mBinding.tabLayout.removeAllTabs();
        mBinding.tabLayout.setVisibility(View.GONE);
    }

    public void addSearchTab(List<SearchNav> searchNavs) {
        for (SearchNav searchNav : searchNavs) {
            switch (searchNav.type()) {
                case 1:
                    mFragments.add(SearchBangumiFragment.newInstance(mKeyword));
                    break;
                case 2:
                    mFragments.add(MainFragment.newInstance());
                    break;
                case 3:
                    mFragments.add(MainFragment.newInstance());
                    break;
                default:
                    return;
            }
            mTitles.add(getTabTitle(searchNav));
        }

        mBinding.viewPager.getAdapter().notifyDataSetChanged();
        mBinding.viewPager.setOffscreenPageLimit(mFragments.size() - 1);

        mBinding.tabLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewUtil.setIndicatorWidth(mBinding.tabLayout, 24);
            }
        });
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.tabLayout.setVisibility(View.VISIBLE);
    }

    private String getTabTitle(SearchNav searchNav) {
        if (searchNav.total() == 0) return searchNav.name();
        String total = searchNav.total() > 99 ? "99+" : String.valueOf(searchNav.total());
        return getString(R.string.search_title, searchNav.name(), total);
    }

    private final class SearchPagerAdapter extends FragmentStatePagerAdapter {

        private SearchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

}
