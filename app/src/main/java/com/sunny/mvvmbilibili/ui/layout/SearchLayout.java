package com.sunny.mvvmbilibili.ui.layout;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.inputmethod.EditorInfo;

/**
 * The type Search layout.
 * Created by Zhou Zejin on 2017/9/29.
 */
public abstract class SearchLayout extends BaseObservable {

    public final ObservableField<String> searchStr = new ObservableField<>();

    abstract public void back();

    abstract public void search();

    public void clear() {
        searchStr.set("");
    }

    public boolean onSearchEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search();
            return true;
        }
        return false;
    }

}
