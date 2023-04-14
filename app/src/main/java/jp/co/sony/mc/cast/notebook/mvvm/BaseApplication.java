package jp.co.sony.mc.cast.notebook.mvvm;
/*
 * © 2021 Sony Corporation.
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;
import jp.co.sony.mc.cast.notebook.mvvm.dp.AppDatabase;

@HiltAndroidApp
public class BaseApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        db = AppDatabase.getInstance(this);
    }

    public static AppDatabase getDb() {return db;}
}
