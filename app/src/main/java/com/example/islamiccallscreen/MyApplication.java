package com.example.islamiccallscreen;

import android.app.Application;

import com.example.islamiccallscreen.util.SharePrefUtils;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SharePrefUtils.init(this);

    }

}

