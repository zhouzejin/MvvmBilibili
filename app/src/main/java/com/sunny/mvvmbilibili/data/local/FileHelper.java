package com.sunny.mvvmbilibili.data.local;

import android.content.Context;
import android.content.res.AssetManager;

import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FileHelper {

    private AssetManager mAssetManager;

    @Inject
    public FileHelper(@ApplicationContext Context context) {
        mAssetManager = context.getAssets();
    }

    public String readAssetsFile(String filename) {
        String str = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                mAssetManager.open(filename)))) {
            StringBuilder builder = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            str = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
