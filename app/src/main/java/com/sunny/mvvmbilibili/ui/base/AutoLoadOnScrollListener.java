package com.sunny.mvvmbilibili.ui.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * The type Auto load on scroll listener.
 * Created by Zhou Zejin on 2017/4/27.
 */
public abstract class AutoLoadOnScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;

    private boolean mIsLoading = false;
    private int mCurrentPage = 1;

    public AutoLoadOnScrollListener(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int totalCount = mLinearLayoutManager.getItemCount();
        int lastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();

        if (!mIsLoading && (lastVisiblePosition >= totalCount - 1) && dy > 0) {
            mIsLoading = true;
            mCurrentPage++;
            onLoadMore(mCurrentPage);
        }
    }

    public abstract void onLoadMore(int currentPage);

    public void setLoading(boolean loading) {
        mIsLoading = loading;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
    }

}
