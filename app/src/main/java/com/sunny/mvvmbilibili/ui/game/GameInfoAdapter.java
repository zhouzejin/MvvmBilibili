package com.sunny.mvvmbilibili.ui.game;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import javax.inject.Inject;

/**
 * The type Game info adapter.
 * Created by Zhou Zejin on 2017/8/21.
 */
public class GameInfoAdapter extends BaseAdapter<GameInfo> {

    private final Context mContext;

    @Inject
    public GameInfoAdapter(@ActivityContext Context context) {
        mContext = context;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.item_game, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = new GameViewModel.GameInfoViewModel(mContext, mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
