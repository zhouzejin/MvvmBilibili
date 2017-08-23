package com.sunny.mvvmbilibili.ui.game;

import com.sunny.mvvmbilibili.data.model.bean.VipGameInfo;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Game mvvm view.
 * Created by Zhou Zejin on 2017/8/18.
 */

public interface GameMvvmView extends MvvmView {

    void backView();

    void goVipGiftView(VipGameInfo vipGameInfo);

}
