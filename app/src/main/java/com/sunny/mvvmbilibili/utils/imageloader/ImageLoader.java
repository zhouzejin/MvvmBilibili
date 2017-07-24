package com.sunny.mvvmbilibili.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.sunny.mvvmbilibili.R;

/**
 * 图片加载功能抽象类
 * <p>
 * Created by Zhou Zejin on 2016/10/10.
 */

public interface ImageLoader {

    /**
     * 可以使用Enum进行封装
     */
    int PIC_LARGE = 0;
    int PIC_MEDIUM = 1;
    int PIC_SMALL = 2;

    /**
     * 可以使用Enum进行封装
     */
    int LOAD_STRATEGY_NORMAL = 0;
    int LOAD_STRATEGY_ONLY_WIFI = 1;

    /**
     * 图片加载参数
     * <p>
     * 使用Builder设计模式
     */
    class DisplayOption {
        /**
         * 类型 (大图，中图，小图)
         */
        private int type;
        /**
         * 当没有成功加载的时候显示的图片
         */
        private int placeHolder;
        /**
         * 加载策略，是否在wifi下才加载
         */
        private int wifiStrategy;

        public int getType() {
            return type;
        }

        public int getPlaceHolder() {
            return placeHolder;
        }

        public int getWifiStrategy() {
            return wifiStrategy;
        }

        private DisplayOption(Builder builder) {
            this.type = builder.type;
            this.placeHolder = builder.placeHolder;
            this.wifiStrategy = builder.wifiStrategy;
        }

        public static final class Builder {
            private int type;
            private int placeHolder;
            private int wifiStrategy;

            public Builder() {
                this.type = PIC_SMALL;
                this.placeHolder = R.mipmap.ic_launcher;
                this.wifiStrategy = LOAD_STRATEGY_NORMAL;
            }

            public Builder type(int type) {
                this.type = type;
                return this;
            }

            public Builder placeHolder(int placeHolder) {
                this.placeHolder = placeHolder;
                return this;
            }

            public Builder strategy(int strategy) {
                this.wifiStrategy = strategy;
                return this;
            }

            public DisplayOption build() {
                return new DisplayOption(this);
            }
        }
    }

    /**
     * 显示图片
     *
     * @param context ImageView的Context
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     */
    void displayImage(Context context, ImageView imageView, String imageUrl, DisplayOption option);

}
