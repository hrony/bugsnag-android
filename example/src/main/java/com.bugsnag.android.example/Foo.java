package com.bugsnag.android.example;

import com.bugsnag.android.lib.LibCrash;

public class Foo {
    void doLibraryCrash() {
        new LibCrash().crashLib();
    }

    void doAppCrash() {
        throw new RuntimeException();
    }
}
