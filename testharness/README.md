# Test Harness

This module provides a test harness app which will throw a fatal/non-fatal exception and send sessions to `localhost:16000`.

This is intended for use with the [hugsnag-mock-api](https://github.com/bugsnag/hugsnag-mock-api), which will determine whether a platform is reporting as expected.

## Script
The `android.sh` script launches an emulator, builds an APK, then installs it and instruments the test.

## Docker
The project uses two Docker images. `bugsnag-android` is intended to be a base image which contains all the dependencies required to build an Android app, such as emulator images. The second runs the test harness.

```
docker build -t bugsnag-android .
docker build -t android-harness .
docker run android-harness
```
