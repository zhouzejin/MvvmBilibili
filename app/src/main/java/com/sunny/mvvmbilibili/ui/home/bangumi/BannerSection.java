package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.LayoutBannerBinding;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.widget.bannerviewpager.ViewPagerAdapter;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Banner section.
 * Created by Zhou Zejin on 2017/9/25.
 */
public class BannerSection extends StatelessSection {

    private ViewPagerAdapter mPagerAdapter;

    public BannerSection(ViewPagerAdapter pagerAdapter) {
        super(new SectionParameters.Builder(R.layout.layout_empty)
                .headerResourceId(R.layout.layout_banner)
                .footerResourceId(R.layout.layout_ad_footer)
                .build());
        mPagerAdapter = pagerAdapter;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        LayoutBannerBinding binding = (LayoutBannerBinding) bindingViewHolder.getBinding();

        if (binding.bannerViewPager.getAdapter() != null) return;
        binding.bannerViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {

    }

}
