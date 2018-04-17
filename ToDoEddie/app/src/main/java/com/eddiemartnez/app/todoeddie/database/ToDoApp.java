package com.eddiemartnez.app.todoeddie.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Eddie Martínez on 5/2/2018.
 */

public class ToDoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
