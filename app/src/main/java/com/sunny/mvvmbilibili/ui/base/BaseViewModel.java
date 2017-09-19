package com.sunny.mvvmbilibili.ui.base;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableField;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;
import com.sunny.mvvmbilibili.widget.CircleProgressView;

import java.util.List;

/**
 * Base class that implements the ViewModel interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvvmView that
 * can be accessed from the children classes by calling getMvvmView().
 */

@BindingMethods({
        @BindingMethod(type = android.widget.ImageView.class, attribute = "android:src", method = "setImageResource"),
})
public class BaseViewModel<T extends MvvmView> extends BaseObservable implements ViewModel<T> {

    private T mMvvmView;

    protected static ImageLoader sImageLoader;

    public final ObservableField<Boolean> isShowProgress = new ObservableField<>();
    public final ObservableField<Boolean> isShowContentEmpty = new ObservableField<>();

    @Override
    public void attachView(T mvvmView) {
        mMvvmView = mvvmView;
    }

    @Override
    public void detachView() {
        mMvvmView = null;
    }

    public boolean isViewAttached() {
        return mMvvmView != null;
    }

    public T getMvvmView() {
        return mMvvmView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvvmViewNotAttachedException();
    }

    public static class MvvmViewNotAttachedException extends RuntimeException {
        public MvvmViewNotAttachedException() {
            super("Please call ViewModel.attachView(MvvmView) before" +
                    " requesting data to the ViewModel");
        }
    }

    @Bindable
    public @DrawableRes int getNavigationIcon() {
        return R.drawable.ic_back;
    }

    @Bindable
    public @StringRes int getToolbarTitle() {
        return R.string.app_name;
    }

    @Bindable
    public @DrawableRes int getContentEmptyImg() {
        return R.drawable.img_default_image;
    }

    @Bindable
    public @StringRes int getContentEmptyHint() {
        return R.string.content_empty;
    }

    public void onClickNavigation() {
    }

    public void showProgress() {
        isShowProgress.set(true);
    }

    public void hideProgress() {
        isShowProgress.set(false);
    }

    /*****
     * BindingAdapter
     *****/

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        RecyclerView.Adapter rawAdapter = recyclerView.getAdapter();
        BaseAdapter<T> adapter;
        if (rawAdapter instanceof HeaderAndFooterWrappedAdapter) {
            adapter = ((HeaderAndFooterWrappedAdapter) rawAdapter).getWrappedAdapter();
        } else if (rawAdapter instanceof BaseAdapter) {
            adapter = (BaseAdapter) recyclerView.getAdapter();
        } else {
            throw new RuntimeException("The adapter is not a BaseAdapter");
        }

        if (adapter != null) {
            adapter.setData(items);
        } else {
            throw new RuntimeException("The data for RecyclerView is null");
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        ImageLoader.DisplayOption option = new ImageLoader.DisplayOption.Builder()
                .placeHolder(R.drawable.img_default_image).build();
        if (sImageLoader != null) {
            sImageLoader.displayImage(imageView.getContext(), imageView, imageUrl, option);
        } else {
            throw new RuntimeException("Please inject ImageLoader in your ViewModel's constructor");
        }
    }

    @BindingAdapter("spin")
    public static void setSpin(CircleProgressView progressView, boolean isSpinning) {
        if (isSpinning) {
            progressView.spin();
        } else {
            progressView.stopSpinning();
        }
    }

    @BindingAdapter("android:drawableTop")
    public static void setDrawableTop(TextView view, @DrawableRes int id) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, id, 0, 0);
    }

}
