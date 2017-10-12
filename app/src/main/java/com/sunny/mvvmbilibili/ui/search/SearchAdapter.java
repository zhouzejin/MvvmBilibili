package com.sunny.mvvmbilibili.ui.search;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.SearchArchive;
import com.sunny.mvvmbilibili.data.model.pojo.SearchMovie;
import com.sunny.mvvmbilibili.data.model.pojo.SearchSeason;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import javax.inject.Inject;

/**
 * The type Search adapter.
 * Created by Zhou Zejin on 2017/10/11.
 */
public class SearchAdapter extends BaseAdapter<Parcelable> {

    private static final int TYPE_SEASON = 0;
    private static final int TYPE_MOVIE = 1;
    private static final int TYPE_ARCHIVE = 2;

    private final SearchViewModel mViewModel;

    @Inject
    public SearchAdapter(SearchViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof SearchSeason) {
            return TYPE_SEASON;
        } else if (mData.get(position) instanceof SearchMovie) {
            return TYPE_MOVIE;
        }
        return TYPE_ARCHIVE;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SEASON:
                return DataBindingUtil.inflate(inflater, R.layout.item_season, parent, false);
            case TYPE_MOVIE:
                return DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);
            case TYPE_ARCHIVE:
                return DataBindingUtil.inflate(inflater, R.layout.item_archive, parent, false);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = null;
        switch (getItemViewType(position)) {
            case TYPE_SEASON:
                viewModel = mViewModel.new SearchSeasonViewModel((SearchSeason) mData.get(position));
                break;
            case TYPE_MOVIE:
                viewModel = mViewModel.new SearchMovieViewModel((SearchMovie) mData.get(position));
                break;
            case TYPE_ARCHIVE:
                viewModel = mViewModel.new SearchArchiveViewModel((SearchArchive) mData.get(position));
                break;
        }
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
