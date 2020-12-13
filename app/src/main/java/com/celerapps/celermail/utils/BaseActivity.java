package com.celerapps.celermail.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity
{
    private static BaseActivity mApp = null;
    /* (non-Javadoc)
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = this;
    }
    public static Context context()
    {
        return App.getInstance().getApplicationContext();
    }
}