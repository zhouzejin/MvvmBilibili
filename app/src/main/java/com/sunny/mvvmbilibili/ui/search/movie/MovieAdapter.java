package com.sunny.mvvmbilibili.ui.search.movie;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.SearchMovie;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import javax.inject.Inject;

/**
 * The type Movie adapter.
 * Created by Zhou Zejin on 2017/10/16.
 */
public class MovieAdapter extends BaseAdapter<SearchMovie> {

    private final SearchMovieViewModel mViewModel;

    @Inject
    public MovieAdapter(SearchMovieViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(inflater, R.layout.item_film, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = mViewModel.new MovieViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
