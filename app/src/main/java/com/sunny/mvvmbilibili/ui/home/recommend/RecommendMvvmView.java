package com.sunny.mvvmbilibili.ui.home.recommend;

import com.sunny.mvvmbilibili.data.model.bean.RecommendBanner;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendBody;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Recommend mvvm view.
 * Created by Zhou zejin on 2017/9/8.
 */
public interface RecommendMvvmView extends MvvmView {

    void showErrorHint();

    void showRecommendInfo();

    void showBannerInfo(RecommendBanner banner);

    void showResultBodyInfo(RecommendBody body);

    void showRankView();

    void showTimelineView();

    void showIndexView();

}
