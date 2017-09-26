package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiHead;
import com.sunny.mvvmbilibili.databinding.PagerBangumiBinding;
import com.sunny.mvvmbilibili.ui.base.ViewModel;
import com.sunny.mvvmbilibili.widget.bannerviewpager.ViewPagerAdapter;

import java.util.Collection;
import java.util.List;

/**
 * The type Bangumi pager adapter.
 * Created by Zhou Zejin on 2017/9/25.
 */
public class BangumiPagerAdapter extends ViewPagerAdapter {

    private BangumiViewModel mViewModel;
    private List<BangumiHead> mBanners;

    public BangumiPagerAdapter(BangumiViewModel bangumiViewModel, List<BangumiHead> banners) {
        mViewModel = bangumiViewModel;
        mBanners = banners;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PagerBangumiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.pager_bangumi, container, false);
        ViewModel viewModel = mViewModel.new BangumiHeadViewModel(mBanners.get(position));
        binding.setVariable(BR.viewmodel, viewModel);
        binding.executePendingBindings(); // 强制绑定后立即刷新，避免数据闪烁
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void addData(Collection<BangumiHead> banners) {
        mBanners.addAll(banners);
    }

    public void clearData() {
        mBanners.clear();
    }

    public BangumiHead getItemData(int position) {
        if (position < 0 || position > mBanners.size() - 1)
            throw new RuntimeException("参数错误！");
        return mBanners.get(position);
    }

}
