package com.sunny.mvvmbilibili.ui.base;

/**
 * Every viewmodel in the app must either implement this interface or extend BaseViewModel
 * indicating the MvvmView type that wants to be attached with.
 */
public interface ViewModel<V extends MvvmView> {

    void attachView(V mvvmView);

    void detachView();
}
