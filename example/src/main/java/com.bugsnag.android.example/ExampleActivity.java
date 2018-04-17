package com.bugsnag.android.example;

import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.lib.LibCrash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ExampleActivity extends AppCompatActivity {

    private Foo foo = new Foo();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bugsnag.setAutoCaptureSessions(true);
        setContentView(R.layout.main);

        findViewById(R.id.btn_fatal_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foo.doLibraryCrash();
            }
        });

        findViewById(R.id.btn_app_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foo.doAppCrash();
            }
        });
    }

}
