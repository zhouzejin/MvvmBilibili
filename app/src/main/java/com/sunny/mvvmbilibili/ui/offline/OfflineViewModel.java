package com.sunny.mvvmbilibili.ui.offline;

import android.content.Context;
import android.databinding.Bindable;
import android.text.format.Formatter;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.FileUtil;

import javax.inject.Inject;

/**
 * The type Offline view model.
 * Created by Zhou Zejin on 2017/8/25.
 */

@ConfigPersistent
public class OfflineViewModel extends BaseViewModel<OfflineMvvmView> {

    private final Context mContext;

    @Inject
    public OfflineViewModel(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public void attachView(OfflineMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void onClickNavigation() {
        getMvvmView().backView();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.offline_cache;
    }

    @Override
    public int getContentEmptyImg() {
        return R.drawable.img_no_cache;
    }

    @Override
    public int getContentEmptyHint() {
        return R.string.no_cache;
    }

    @Bindable
    public int getStorageProgress() {
        return countProgress(FileUtil.getPhoneTotalSize(), FileUtil.getPhoneAvailableSize());
    }

    @Bindable
    public String getStorageInfo() {
        // 转换为G的显示单位
        String totalSize = Formatter.formatFileSize(mContext, FileUtil.getPhoneTotalSize());
        String availableSize = Formatter.formatFileSize(mContext, FileUtil.getPhoneAvailableSize());
        return mContext.getString(R.string.cache_info, totalSize, availableSize);
    }

    private int countProgress(long phoneTotalSize, long phoneAvailableSize) {
        double totalSize = phoneTotalSize / (1024 * 3);
        double availableSize = phoneAvailableSize / (1024 * 3);
        // 取整相减
        int size = (int) (Math.floor(totalSize) - Math.floor(availableSize));
        double v = (size / Math.floor(totalSize)) * 100;
        return (int) Math.floor(v);
    }

}
