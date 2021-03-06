package com.sunny.mvvmbilibili.ui.home.live;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.LiveBanner;
import com.sunny.mvvmbilibili.databinding.PagerLiveBinding;
import com.sunny.mvvmbilibili.ui.base.ViewModel;
import com.sunny.mvvmbilibili.widget.bannerviewpager.ViewPagerAdapter;

import java.util.Collection;
import java.util.List;

/**
 * The type Live pager adapter.
 * Created by Zhou Zejin on 2017/9/21.
 */
public class LivePagerAdapter extends ViewPagerAdapter {

    private LiveViewModel mViewModel;
    private List<LiveBanner> mBanners;

    public LivePagerAdapter(LiveViewModel liveViewModel, List<LiveBanner> banners) {
        mViewModel = liveViewModel;
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
        PagerLiveBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.pager_live, container, false);
        ViewModel viewModel = mViewModel.new BannerViewModel(mBanners.get(position));
        binding.setVariable(BR.viewmodel, viewModel);
        binding.executePendingBindings(); // 强制绑定后立即刷新，避免数据闪烁
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void addData(Collection<LiveBanner> banners) {
        mBanners.addAll(banners);
    }

    public void clearData() {
        mBanners.clear();
    }

    public LiveBanner getItemData(int position) {
        if (position < 0 || position > mBanners.size() - 1)
            throw new RuntimeException("参数错误！");
        return mBanners.get(position);
    }

}
