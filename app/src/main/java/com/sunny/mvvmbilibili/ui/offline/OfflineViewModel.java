package com.sunny.mvvmbilibili.ui.offline;

import android.content.Context;
import android.databinding.ObservableField;
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

    // These observable fields will update Views automatically
    public final ObservableField<Integer> progressValue = new ObservableField<>();
    public final ObservableField<String> cacheInfo = new ObservableField<>();

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

    public void getStorageStatus() {
        long phoneTotalSize = FileUtil.getPhoneTotalSize();
        long phoneAvailableSize = FileUtil.getPhoneAvailableSize();
        // 计算占用空间的百分比
        int progress = countProgress(phoneTotalSize, phoneAvailableSize);
        // 转换为G的显示单位
        String totalSize = Formatter.formatFileSize(mContext, phoneTotalSize);
        String availableSize = Formatter.formatFileSize(mContext, phoneAvailableSize);

        progressValue.set(progress);
        cacheInfo.set(mContext.getString(R.string.cache_info, totalSize, availableSize));
        isShowContentEmpty.set(true);
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
