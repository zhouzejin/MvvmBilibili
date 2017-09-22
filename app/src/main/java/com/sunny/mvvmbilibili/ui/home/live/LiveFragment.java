package com.sunny.mvvmbilibili.ui.home.live;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.LiveBanner;
import com.sunny.mvvmbilibili.data.model.bean.LiveEntranceIcons;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfo;
import com.sunny.mvvmbilibili.data.model.bean.LivePartitions;
import com.sunny.mvvmbilibili.databinding.FragmentLiveBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.LazyLoadFragment;
import com.sunny.mvvmbilibili.ui.browser.BrowserActivity;
import com.sunny.mvvmbilibili.utils.SnackbarUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * The type Live fragment.
 * Created by Zhou Zejin on 2017/9/21.
 */
public class LiveFragment extends LazyLoadFragment implements LiveMvvmView {

    private static final int SPAN_COUNT = 2;
    private static final int HEADER_SPAN_SIZE = 2;
    private static final int FOOTER_SPAN_SIZE = 2;
    private static final int CONTENT_SPAN_SIZE = 1;

    @Inject LiveViewModel mViewModel;
    @Inject @FragmentContext Context mContext;

    private FragmentLiveBinding mBinding;
    private SectionedRecyclerViewAdapter mSectionedAdapter;

    public LiveFragment() {
    }

    public static LiveFragment newInstance() {
        return new LiveFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_live, container, false);
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
    public void showLiveInfo() {
        mSectionedAdapter.removeAllSections();
        mSectionedAdapter.addSection(new BannerSection(mViewModel,
                new LivePagerAdapter(mViewModel, mViewModel.banners)));
        for (LivePartitions partitions : mViewModel.partitions) {
            mSectionedAdapter.addSection(new PartitionSection(mViewModel, partitions));
        }
        mSectionedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBannerView(LiveBanner banner) {
        startActivity(BrowserActivity.getStartIntent(mContext, banner.link(), banner.title()));
    }

    @Override
    public void showEntranceView(LiveEntranceIcons entranceIcons) {
        // TODO
        ToastUtil.showShort(mContext, entranceIcons.name());
    }

    @Override
    public void showLiveView(LiveInfo liveInfo) {
        // TODO
        ToastUtil.showShort(mContext, liveInfo.title());
    }

}
