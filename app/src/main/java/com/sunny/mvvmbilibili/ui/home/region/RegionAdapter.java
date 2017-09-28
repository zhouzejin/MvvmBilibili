package com.sunny.mvvmbilibili.ui.home.region;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.Region;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import javax.inject.Inject;

/**
 * The type Region adapter.
 * Created by Zhou Zejin on 2017/9/26.
 */

public class RegionAdapter extends BaseAdapter<Region> {

    private final RegionViewModel mViewModel;

    @Inject
    public RegionAdapter(RegionViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(inflater, R.layout.item_region, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = mViewModel.new RegionItemViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
