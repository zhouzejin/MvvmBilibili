package com.sunny.mvvmbilibili.ui.layout;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * The type Progress layout.
 * Created by Zhou Zejin on 2017/9/28.
 */
public class ProgressLayout extends BaseObservable {

    public final ObservableField<Boolean> isShowProgress = new ObservableField<>();

    public void showProgress() {
        isShowProgress.set(true);
    }

    public void hideProgress() {
        isShowProgress.set(false);
    }

}
