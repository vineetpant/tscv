package com.social.dial;

import android.app.Application;
import android.content.Context;

/**
 * Created by SCL0958 on 14-05-2015.
 */
public class MyApplication extends Application {

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
    }

    public static Context getAppContext(){
        return appContext;
    }
}
