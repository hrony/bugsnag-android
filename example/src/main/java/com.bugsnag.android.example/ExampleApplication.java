package com.bugsnag.android.example;

import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Configuration;

import android.app.Application;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration config = new Configuration("e7cfd6156fa5de611558c49bb7a638c7");
        config.setEndpoint("http://10.0.2.2:8000");
        Bugsnag.init(this, config);
    }

}
