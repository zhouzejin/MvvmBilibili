package com.sunny.mvvmbilibili.ui.home.region;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.Region;
import com.sunny.mvvmbilibili.databinding.FragmentRegionBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.LazyLoadFragment;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

/**
 * The type Region fragment.
 * Created by Zhou Zejin on 2017/9/26.
 */
public class RegionFragment extends LazyLoadFragment implements RegionMvvmView {

    private final static int SPAN_COUNT = 3;

    @Inject RegionViewModel mViewModel;
    @Inject @FragmentContext Context mContext;
    @Inject RegionAdapter mAdapter;

    private FragmentRegionBinding mBinding;

    public RegionFragment() {

    }

    public static RegionFragment newInstance() {
        return new RegionFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_region, container, false);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, SPAN_COUNT));
        mBinding.recyclerView.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mViewModel.loadData();
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
    public void showRegionView(Region region) {
        // TODO
        ToastUtil.showShort(mContext, region.title());
    }

}
