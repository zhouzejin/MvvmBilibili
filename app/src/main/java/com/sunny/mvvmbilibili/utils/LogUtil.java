package com.sunny.mvvmbilibili.utils;

import com.sunny.mvvmbilibili.BuildConfig;

import timber.log.Timber;

/**
 * Created by Zhou Zejin on 2016/10/9.
 * A log util wrap from Timber.
 */

public class LogUtil {

    public static void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    /**
     * Log an verbose message with optional format args.
     */
    public static void v(String message, Object... args) {
        Timber.v(message, args);
    }

    /**
     * Log an verbose exception and a message with optional format args.
     */
    public static void v(Throwable t, String message, Object... args) {
        Timber.v(t, message, args);
    }

    /**
     * Log an debug message with optional format args.
     */
    public static void d(String message, Object... args) {
        Timber.d(message, args);
    }

    /**
     * Log an debug exception and a message with optional format args.
     */
    public static void d(Throwable t, String message, Object... args) {
        Timber.d(t, message, args);
    }

    /**
     * Log an info message with optional format args.
     */
    public static void i(String message, Object... args) {
        Timber.i(message, args);
    }

    /**
     * Log an info exception and a message with optional format args.
     */
    public static void i(Throwable t, String message, Object... args) {
        Timber.i(t, message, args);
    }

    /**
     * Log a warning message with optional format args.
     */
    public static void w(String message, Object... args) {
        Timber.w(message, args);
    }

    /**
     * Log a warning exception and a message with optional format args.
     */
    public static void w(Throwable t, String message, Object... args) {
        Timber.w(t, message, args);
    }

    /**
     * Log an error message with optional format args.
     */
    public static void e(String message, Object... args) {
        Timber.e(message, args);
    }

    /**
     * Log an error exception and a message with optional format args.
     */
    public static void e(Throwable t, String message, Object... args) {
        Timber.e(t, message, args);
    }

}
