package com.sunny.mvvmbilibili.widget.bannerviewpager;


public interface DataSetSubject {
    void registerSubscriber(DataSetSubscriber subscriber);
    void removeSubscriber(DataSetSubscriber subscriber);
    void notifySubscriber();
}
