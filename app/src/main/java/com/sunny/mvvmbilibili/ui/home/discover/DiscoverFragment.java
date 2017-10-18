package com.sunny.mvvmbilibili.ui.home.discover;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.HotWord;
import com.sunny.mvvmbilibili.databinding.FragmentDiscoverBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.BaseFragment;
import com.sunny.mvvmbilibili.ui.search.SearchActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import javax.inject.Inject;

/**
 * The type Discover fragment.
 * Created by Administrator on 2017/10/17.
 */
public class DiscoverFragment extends BaseFragment implements DiscoverMvvmView {

    @Inject DiscoverViewModel mViewModel;
    @Inject @FragmentContext Context mContext;

    private FragmentDiscoverBinding mBinding;

    public DiscoverFragment() {

    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        super.onViewCreated(view, savedInstanceState);
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
    public void showHotWord() {
        mBinding.tagFlowShow.setAdapter(new TagAdapter<HotWord>(mViewModel.items.subList(0, 7)){
            @Override
            public View getView(FlowLayout parent, int position, HotWord hotWord) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_tag, parent, false);
                binding.setVariable(BR.viewmodel, mViewModel.new HowWordViewModel(hotWord));
                return binding.getRoot();
            }
        });
        mBinding.tagFlowGone.setAdapter(new TagAdapter<HotWord>(mViewModel.items){
            @Override
            public View getView(FlowLayout parent, int position, HotWord hotWord) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_tag, parent, false);
                binding.setVariable(BR.viewmodel, mViewModel.new HowWordViewModel(hotWord));
                return binding.getRoot();
            }
        });
    }

    @Override
    public void goSearchView(String queryStr) {
        mContext.startActivity(SearchActivity.getStartIntent(mContext, queryStr));
    }

}
