package com.sunny.mvvmbilibili.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sunny.mvvmbilibili.BuildConfig;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.utils.NetworkUtil;
import com.sunny.mvvmbilibili.utils.factory.MyGsonTypeAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitHelper {

    private static final int DEFAULT_TIMEOUT = 7;
    private static final String USR_AGENT = "OhMyBiliBili Android Client/2.1 (100332338@qq.com)";

    private static OkHttpClient sHttpClient;
    private static OkHttpClient.Builder sHttpClientBuilder = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);
    private static Gson sGson = new GsonBuilder()
            .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    static {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            sHttpClientBuilder.addInterceptor(logging);
        }
    }

    private static void initOkHttpClient(@ApplicationContext final Context context) {
        synchronized (RetrofitHelper.class) {
            // 缓存文件设置
            final String CACHE_FILE_NAME = "RetrofitHttpCache";
            final long CACHE_SIZE = 1024 * 1024 * 100;
            final Cache CACHE_FILE = new Cache(new File(context.getCacheDir(), CACHE_FILE_NAME),
                    CACHE_SIZE);

            // 设置HTTP缓存拦截器
            final int CACHE_TIME = 60 * 60 * 24 * 7;
            final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetworkUtil.isNetworkConnected(context)) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }

                    Response originalResponse = chain.proceed(request);
                    if (NetworkUtil.isNetworkConnected(context)) {
                        String cacheControl = request.cacheControl().toString();
                        return originalResponse.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale="
                                        + CACHE_TIME)
                                .removeHeader("Pragma")
                                .build();
                    }
                }
            };

            // 设置UA拦截器，B站API需要加上UA才能正常使用
            final Interceptor USER_AGENT_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request requestWithUserAgent = originalRequest.newBuilder()
                            .removeHeader("User-Agent")
                            .addHeader("User-Agent", USR_AGENT)
                            .build();
                    return chain.proceed(requestWithUserAgent);
                }
            };

            // REWRITE_CACHE_CONTROL_INTERCEPTOR拦截器需要同时设置networkInterceptors和interceptors
            sHttpClient = sHttpClientBuilder
                    .cache(CACHE_FILE)
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(USER_AGENT_INTERCEPTOR)
                    .build();
        }
    }

    private static <T> T createApiService(@ApplicationContext Context context, Class<T> clazz,
                                          String baseUrl) {
        initOkHttpClient(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(sHttpClient)
                .addConverterFactory(GsonConverterFactory.create(sGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    private RetrofitService mRetrofitService;

    @Inject
    public RetrofitHelper(@ApplicationContext Context context) {
        mRetrofitService = createApiService(context, RetrofitService.class, RetrofitService.ENDPOINT);
    }

    public RetrofitService getRetrofitService() {
        return mRetrofitService;
    }

}
