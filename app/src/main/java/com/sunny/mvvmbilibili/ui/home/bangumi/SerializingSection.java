package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.BangumiSerializing;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Serializing section.
 * Created by Zhou Zejin on 2017/9/25.
 */
public class SerializingSection extends StatelessSection {

    private BangumiViewModel mViewModel;
    private List<BangumiSerializing> mSerializings;

    public SerializingSection(BangumiViewModel viewModel, List<BangumiSerializing> serializings) {
        super(new SectionParameters.Builder(R.layout.layout_serializing_item)
                .headerResourceId(R.layout.layout_serializing_header)
                .footerResourceId(R.layout.layout_serializing_footer)
                .build());
        mViewModel = viewModel;
        mSerializings = serializings;
    }

    @Override
    public int getContentItemsTotal() {
        return mSerializings.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new SerializingBodyViewModel(mSerializings.get(position));
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        if (mViewModel.bangumiAd.get().body().size() < 1) return;
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new BangumiBodyViewModel(mViewModel.bangumiAd.get().body().get(0));
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

}
