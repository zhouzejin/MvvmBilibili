package com.sunny.mvvmbilibili.utils;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

/**
 * The type Snackbar util.
 * Created by Zhou Zejin on 2017/9/11.
 */
public class SnackbarUtil {

    public static void showShort(View view, String text) {
        if (view == null || TextUtils.isEmpty(text)) return;
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void showLong(View view, String text) {
        if (view == null || TextUtils.isEmpty(text)) return;
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

}
