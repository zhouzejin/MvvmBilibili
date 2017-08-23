package com.sunny.mvvmbilibili.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 复制剪贴工具类
 */
@Singleton
public class ClipboardUtil {

    private final Context mContext;
    private final ClipboardManager mClipboardManager;

    @Inject
    public ClipboardUtil(@ApplicationContext Context context) {
        mContext = context;
        mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * 为剪切板设置内容
     */
    public void setText(CharSequence label, CharSequence text) {
        ClipData clip = ClipData.newPlainText(label, text);
        mClipboardManager.setPrimaryClip(clip);
    }

    /**
     * 获取剪切板的内容
     */
    public CharSequence getText() {
        StringBuilder sb = new StringBuilder();
        if (mClipboardManager.hasPrimaryClip()) {
            ClipData clipData = mClipboardManager.getPrimaryClip();
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item item = clipData.getItemAt(i);
                CharSequence str = item.coerceToText(mContext);
                sb.append(str);
            }
        }
        return sb.toString();
    }

}
