package com.bugsnag.android.example;

import static android.widget.Toast.LENGTH_SHORT;

import com.bugsnag.android.BeforeNotify;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Callback;
import com.bugsnag.android.Error;
import com.bugsnag.android.MetaData;
import com.bugsnag.android.Report;
import com.bugsnag.android.Severity;
import com.bugsnag.android.other.CrashyClass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bugsnag.setAutoCaptureSessions(true);
        setContentView(R.layout.main);
        setupToolbarLogo();
        performAdditionalBugsnagSetup();

        findViewById(R.id.btn_fatal_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAppCrash();
            }
        });
    }

    void doLibraryCrash() {

    }

    void doAppCrash() {
        throw new RuntimeException();
    }
}
