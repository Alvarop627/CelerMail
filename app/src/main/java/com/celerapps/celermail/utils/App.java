package com.celerapps.celermail.utils;

import android.content.Context;

public class App extends android.app.Application
{
    private static App mApp = null;
    /* (non-Javadoc)
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        mApp = this;
    }
    public static Context context()
    {
        return mApp.getApplicationContext();
    }

    public static App getInstance(){
        return mApp;
    }
}