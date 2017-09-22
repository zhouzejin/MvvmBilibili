package com.sunny.mvvmbilibili.ui.home.live;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.LiveEntranceIcons;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

/**
 * The type Entrance adapter.
 * Created by Zhou Zejin on 2017/9/21.
 */

public class EntranceAdapter extends BaseAdapter<LiveEntranceIcons> {

    private final LiveViewModel mViewModel;

    public EntranceAdapter(LiveViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.item_entrance, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = mViewModel.new EntranceIconViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
