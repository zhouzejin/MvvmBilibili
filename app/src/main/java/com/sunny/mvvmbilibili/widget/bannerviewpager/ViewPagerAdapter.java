package com.sunny.mvvmbilibili.widget.bannerviewpager;

import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager's adapter.It use {@link DataSetSubscriber} and {@link DataSetSubject} to tell
 * {@link ViewPagerIndicator} that the dataset has been changed,so ViewPagerIndicator will
 * requestlayout to adjust the new state.
 *
 * @author Chen Yu
 * @version 1.0.0
 * @date 2016-10-03
 */
public abstract class ViewPagerAdapter extends PagerAdapter implements DataSetSubject {

    private List<DataSetSubscriber> mSubscribers = new ArrayList<>();

    private OnPageClickListener mOnPageClickListener;

    public OnPageClickListener getOnPageClickListener() {
        return mOnPageClickListener;
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        mOnPageClickListener = onPageClickListener;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        notifySubscriber();
    }

    @Override
    public void registerSubscriber(DataSetSubscriber subscriber) {
        mSubscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(DataSetSubscriber subscriber) {
        mSubscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for (DataSetSubscriber subscriber : mSubscribers) {
            subscriber.update(getCount());
        }
    }

}
