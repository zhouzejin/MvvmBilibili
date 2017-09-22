package com.sunny.mvvmbilibili.ui.home.live;

import com.sunny.mvvmbilibili.data.model.bean.LiveBanner;
import com.sunny.mvvmbilibili.data.model.bean.LiveEntranceIcons;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfo;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Live mvvm view.
 * Created by Zhou Zejin on 2017/9/21.
 */
public interface LiveMvvmView extends MvvmView {

    void showErrorHint();

    void showLiveInfo();

    void showBannerView(LiveBanner banner);

    void showEntranceView(LiveEntranceIcons entranceIcons);

    void showLiveView(LiveInfo liveInfo);

}
