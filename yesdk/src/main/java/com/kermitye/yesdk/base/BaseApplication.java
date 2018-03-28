package com.kermitye.yesdk.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by kermitye on 2018/3/16.
 */

public class BaseApplication extends Application {

    private static final String LOG_TAG = "YZ_LOGGER";
    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;
    private static BaseApplication mApp;

    public static synchronized BaseApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}
