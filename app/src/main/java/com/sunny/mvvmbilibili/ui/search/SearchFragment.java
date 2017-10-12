package com.sunny.mvvmbilibili.ui.search;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.FragmentSearchBinding;
import com.sunny.mvvmbilibili.databinding.LayoutSearchFooterBinding;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.AutoLoadOnScrollListener;
import com.sunny.mvvmbilibili.ui.base.HeaderAndFooterWrappedAdapter;
import com.sunny.mvvmbilibili.ui.base.LazyLoadFragment;

import javax.inject.Inject;

/**
 * The type Search fragment.
 * Created by Zhou Zejin on 2017/10/9.
 */
public class SearchFragment extends LazyLoadFragment implements SearchMvvmView {

    private static final String ARGUMENTS_QUERY_STRING =
            "com.sunny.mvvmbilibili.ui.search.SearchFragment.ARGUMENTS_QUERY_STRING";

    @Inject SearchViewModel mViewModel;
    @Inject Activity mActivity;
    @Inject SearchAdapter mAdapter;
    @Inject @ActivityContext Context mContext;

    private FragmentSearchBinding mBinding;
    private AutoLoadOnScrollListener mScrollListener;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String queryStr) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENTS_QUERY_STRING, queryStr);
        fragment.setArguments(bundle);
        return fragment;
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        initRecyclerView();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        initAdapter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mViewModel.search(getArguments().getString(ARGUMENTS_QUERY_STRING), 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.detachView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mScrollListener = new AutoLoadOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mViewModel.search(getArguments().getString(ARGUMENTS_QUERY_STRING), currentPage);
            }
        };
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.addOnScrollListener(mScrollListener);
    }

    private void initAdapter() {
        HeaderAndFooterWrappedAdapter wrappedAdapter = new HeaderAndFooterWrappedAdapter(mAdapter);
        LayoutSearchFooterBinding footer = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.layout_search_footer, mBinding.recyclerView, false);
        footer.setLayout(mViewModel.searchFooterLayout);
        wrappedAdapter.addFooterView(footer);
        mBinding.recyclerView.setAdapter(wrappedAdapter);
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void showSearchNav() {
        if (mActivity instanceof SearchActivity) {
            ((SearchActivity)mActivity).addSearchTab(mViewModel.searchNavs);
        }
    }

    @Override
    public void showSearching() {
        ((AnimationDrawable)mBinding.ivSearching.getDrawable()).start();
    }

    @Override
    public void hideSearching() {
        ((AnimationDrawable)mBinding.ivSearching.getDrawable()).stop();
    }

    @Override
    public void setRecyclerScrollLoading(boolean isLoading) {
        mScrollListener.setLoading(isLoading);
    }

    @Override
    public void setCurrentPage(int pageNum) {
        mScrollListener.setCurrentPage(pageNum);
    }

}
