package com.sunny.mvvmbilibili.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public final class ViewUtil {

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * http://blog.csdn.net/u013134391/article/details/70833903
     *
     * @param tabLayout
     * @param marginDp
     */
    public static void setIndicatorWidth(TabLayout tabLayout, int marginDp) {
        // 拿到TabLayout的mTabStrip属性
        LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

        int marginPx = dpToPx(marginDp);

        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View tabView = mTabStrip.getChildAt(i);

            //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
            Field mTextViewField = null;
            try {
                mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            TextView mTextView = null;
            try {
                if (mTextViewField != null)
                    mTextView = (TextView) mTextViewField.get(tabView);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            tabView.setPadding(0, 0, 0, 0);

            //因为我想要的效果是  字多宽线就多宽，所以测量mTextView的宽度
            int width;
            if (mTextView == null) return;
            width = mTextView.getWidth();
            if (width == 0) {
                mTextView.measure(0, 0);
                width = mTextView.getMeasuredWidth();
            }

            //设置tab左右间距为10dp  注意这里不能使用Padding  因为源码中线的宽度是根据  tabView的宽度来设置的
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
            params.width = width;
            params.leftMargin = marginPx;
            params.rightMargin = marginPx;
            tabView.setLayoutParams(params);

            tabView.invalidate();
        }
    }

}
