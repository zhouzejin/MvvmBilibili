package com.sunny.mvvmbilibili.ui.home.bangumi;

import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;
import com.sunny.mvvmbilibili.data.model.bean.BangumiSerializing;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiBody;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiHead;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiList;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The type Bangumi mvvm view.
 * Created by Zhou Zejin on 2017/9/25.
 */
public interface BangumiMvvmView extends MvvmView {

    void showErrorHint();

    void showBangumiInfo();

    void showBangumiHeadView(BangumiHead bangumiHead);

    void showBangumiBodyView(BangumiBody bangumiBody);

    void showSerializingBodyView(BangumiSerializing bangumiSerializing);

    void showBangumiListView(BangumiList bangumiList);

    void showRecommendResultView(BangumiRecommendResult bangumiRecommendResult);

}
