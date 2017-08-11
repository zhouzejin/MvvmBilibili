package com.sunny.mvvmbilibili.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * The type Toast util.
 * Created by Zhou Zejin on 2017/8/11.
 */
public class ToastUtil {

    public static void showShort(Context context, String text) {
        if (context == null || TextUtils.isEmpty(text)) return;
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {
        if (context == null || TextUtils.isEmpty(text)) return;
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
