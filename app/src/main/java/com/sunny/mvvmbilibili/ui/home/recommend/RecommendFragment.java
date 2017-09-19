package com.sunny.mvvmbilibili.ui.home.recommend;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.RecommendBanner;
import com.sunny.mvvmbilibili.data.model.bean.RecommendResult;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendBody;
import com.sunny.mvvmbilibili.databinding.FragmentRecommendBinding;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;
import com.sunny.mvvmbilibili.ui.base.BaseFragment;
import com.sunny.mvvmbilibili.ui.browser.BrowserActivity;
import com.sunny.mvvmbilibili.utils.SnackbarUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * The type Recommend fragment.
 * Created by Zhou Zejin on 2017/9/8.
 */
public class RecommendFragment extends BaseFragment implements RecommendMvvmView {

    public final static Map<String, Integer> sIconMap = new HashMap<>();

    static {
        sIconMap.put("热门焦点", R.drawable.ic_category_hot);
        sIconMap.put("正在直播", R.drawable.ic_category_live);
        sIconMap.put("番剧区", R.drawable.ic_category_bangumi);
        sIconMap.put("动画区", R.drawable.ic_category_animation);
        sIconMap.put("国创区", R.drawable.ic_category_creation);
        sIconMap.put("音乐区", R.drawable.ic_category_music);
        sIconMap.put("舞蹈区", R.drawable.ic_category_dance);
        sIconMap.put("游戏区", R.drawable.ic_category_game);
        sIconMap.put("鬼畜区", R.drawable.ic_category_ghost);
        sIconMap.put("生活区", R.drawable.ic_category_life);
        sIconMap.put("科技区", R.drawable.ic_category_science);
        sIconMap.put("活动中心", R.drawable.ic_category_activity);
        sIconMap.put("时尚区", R.drawable.ic_category_fashion);
        sIconMap.put("娱乐区", R.drawable.ic_category_recreation);
        sIconMap.put("电视剧区", R.drawable.ic_category_tv);
        sIconMap.put("电影区", R.drawable.ic_category_film);
    }

    public static final String TYPE_RECOMMEND = "recommend";
    public static final String TYPE_LIVE = "live";
    public static final String TYPE_BANGUMI_2 = "bangumi_2";
    public static final String TYPE_REGION = "region";
    public static final String TYPE_ACTIVITY = "activity";

    private static final int SPAN_COUNT = 2;
    private static final int HEADER_SPAN_SIZE = 2;
    private static final int FOOTER_SPAN_SIZE = 2;
    private static final int CONTENT_SPAN_SIZE = 1;

    @Inject
    RecommendViewModel mViewModel;
    @Inject
    @FragmentContext
    Context mContext;

    private FragmentRecommendBinding mBinding;
    private SectionedRecyclerViewAdapter mSectionedAdapter;

    public RecommendFragment() {

    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false);
        mSectionedAdapter = new SectionedRecyclerViewAdapter();
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, SPAN_COUNT);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return HEADER_SPAN_SIZE;
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return FOOTER_SPAN_SIZE;
                    default:
                        return CONTENT_SPAN_SIZE;
                }
            }
        });
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setAdapter(mSectionedAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        mViewModel.refresh();
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
    public void showErrorHint() {
        SnackbarUtil.showShort(mBinding.recyclerView, getString(R.string.data_load_error));
    }

    @Override
    public void showRecommendInfo() {
        mSectionedAdapter.removeAllSections();
        mSectionedAdapter.addSection(new BannerSection(
                new RecommendPagerAdapter(mViewModel, mViewModel.banners)));
        for (RecommendResult result : mViewModel.results) {
            switch (result.type()) {
                case TYPE_RECOMMEND:
                    mSectionedAdapter.addSection(new RecommendSection(mViewModel, result));
                    break;
                case TYPE_LIVE:
                    mSectionedAdapter.addSection(new LiveSection(mViewModel, result));
                    break;
                case TYPE_BANGUMI_2:
                    mSectionedAdapter.addSection(new BangumiSection(mViewModel, result));
                    break;
                case TYPE_REGION:
                    mSectionedAdapter.addSection(new RegionSection(mViewModel, result));
                    break;
                case TYPE_ACTIVITY:
                    mSectionedAdapter.addSection(new ActivitySection(mViewModel, result));
                    break;
                default:
                    ToastUtil.showShort(mContext, getString(R.string.recommend_type_undefine));
            }
        }
        mSectionedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBannerInfo(RecommendBanner banner) {
        startActivity(BrowserActivity.getStartIntent(mContext, banner.value(), banner.title()));
    }

    @Override
    public void showResultBodyInfo(RecommendBody body) {
        // TODO
        ToastUtil.showShort(mContext, body.title());
    }

    @Override
    public void showRankView() {
        // TODO
        ToastUtil.showShort(mContext, "排行榜");
    }

    @Override
    public void showTimelineView() {
        // TODO
        ToastUtil.showShort(mContext, "新番放送");
    }

    @Override
    public void showIndexView() {
        // TODO
        ToastUtil.showShort(mContext, "番剧索引");
    }

}
