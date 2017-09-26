package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;
import com.sunny.mvvmbilibili.data.model.bean.BangumiSerializing;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiBody;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiHead;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiList;
import com.sunny.mvvmbilibili.databinding.FragmentBangumiBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.LazyLoadFragment;
import com.sunny.mvvmbilibili.ui.browser.BrowserActivity;
import com.sunny.mvvmbilibili.utils.SnackbarUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * The type Bangumi fragment.
 * Created by Zhou Zejin on 2017/9/25.
 */
public class BangumiFragment extends LazyLoadFragment implements BangumiMvvmView {

    public final static Integer[] sIconArray = new Integer[]{
            R.drawable.ic_bangumi_winter,
            R.drawable.ic_bangumi_spring,
            R.drawable.ic_bangumi_summer,
            R.drawable.ic_bangumi_autumn,
    };

    private static final int SPAN_COUNT = 3;
    private static final int HEADER_SPAN_SIZE = 3;
    private static final int FOOTER_SPAN_SIZE = 3;
    private static final int CONTENT_SPAN_SIZE = 1;

    public static String[] sTitleArray;

    @Inject BangumiViewModel mViewModel;
    @Inject @FragmentContext Context mContext;

    private FragmentBangumiBinding mBinding;
    private SectionedRecyclerViewAdapter mSectionedAdapter;

    public BangumiFragment() {

    }

    public static BangumiFragment newInstance() {
        return new BangumiFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        sTitleArray = mContext.getResources().getStringArray(R.array.bangumi_season);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bangumi, container, false);
        mSectionedAdapter = new SectionedRecyclerViewAdapter();
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, SPAN_COUNT);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return HEADER_SPAN_SIZE;
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return FOOTER_SPAN_SIZE;
                    default:
                        return CONTENT_SPAN_SIZE;
                }
            }
        });
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setAdapter(mSectionedAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mViewModel.refresh();
    }

    @Override
    public void onDestroyView() {
        mViewModel.detachView();
        super.onDestroyView();
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void showErrorHint() {
        SnackbarUtil.showShort(mBinding.recyclerView, getString(R.string.data_load_error));
    }

    @Override
    public void showBangumiInfo() {
        mSectionedAdapter.removeAllSections();
        mSectionedAdapter.addSection(new BannerSection(
                new BangumiPagerAdapter(mViewModel, mViewModel.bangumiAd.get().head())));
        mSectionedAdapter.addSection(new SerializingSection(mViewModel, mViewModel.bangumiSerializings));
        mSectionedAdapter.addSection(new PreviousSection(mViewModel, mViewModel.bangumiPrevious.get()));
        mSectionedAdapter.addSection(new ResultSection(mViewModel));
        mSectionedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBangumiHeadView(BangumiHead bangumiHead) {
        startActivity(BrowserActivity.getStartIntent(mContext, bangumiHead.link(), bangumiHead.title()));
    }

    @Override
    public void showBangumiBodyView(BangumiBody bangumiBody) {
        startActivity(BrowserActivity.getStartIntent(mContext, bangumiBody.link(), bangumiBody.title()));
    }

    @Override
    public void showSerializingBodyView(BangumiSerializing bangumiSerializing) {
        // TODO
        ToastUtil.showShort(mContext, bangumiSerializing.title());
    }

    @Override
    public void showBangumiListView(BangumiList bangumiList) {
        // TODO
        ToastUtil.showShort(mContext, bangumiList.title());
    }

    @Override
    public void showRecommendResultView(BangumiRecommendResult bangumiRecommendResult) {
        startActivity(BrowserActivity.getStartIntent(mContext,
                bangumiRecommendResult.link(), bangumiRecommendResult.title()));
    }

}
