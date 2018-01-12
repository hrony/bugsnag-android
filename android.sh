#!/bin/bash

function launch_emulator() {
  # always run from tools dir rather than android home due to rel path bug
  cd $ANDROID_HOME/tools
  echo "Launching Android Emulator"

  # see https://developer.android.com/studio/run/emulator-commandline.html#starting
  emulator @NexusEmulator -verbose -no-window -no-boot-anim -no-audio &
  EMULATOR_PID=$!
  wait_for_device
  cd ${HARNESS_DIR}
}

# Waits for boot to complete (see https://android.stackexchange.com/a/83747)
function wait_for_device() {
    echo "Waiting for device..."
    adb wait-for-device
    echo "Device booting, this may take several minutes..."

    A=$(adb shell getprop sys.boot_completed | tr -d '\r')

    while [ "$A" != "1" ]; do
        sleep 2
        echo "Polling device status"
        A=$(adb shell getprop sys.boot_completed | tr -d '\r')
    done
    echo "Device ready!"
}

function build_test_app() {
  echo "Running Android Tests"
  cd ${PROJ_DIR}
  ./gradlew testharness:assembleRelease
}

function poll_app() {
  # Detect whether app is still in the foreground (app kills its own process when completed)
  while [[ `adb shell dumpsys activity | grep "Proc # 0" | grep "com.bugsnag.android.hugsnag"` ]];
   do echo "Polling Android App"
   sleep 2
  done
}

function launch_test_app() {
  echo "Installing test app"
  adb install testharness/build/outputs/apk/release/testharness-release.apk
  adb shell am start -n com.bugsnag.android.hugsnag/com.bugsnag.android.MainActivity
  echo "Started MainActivity"
  poll_app

  echo "Killing app process"
  adb shell am force-stop com.bugsnag.android.hugsnag

  # launch again, with session sending enabled
  sleep 2 # wait for app to close
  echo "Launching MainActivity again"
  adb shell am start -n com.bugsnag.android.hugsnag/com.bugsnag.android.MainActivity --ez sendSessions true
  poll_app
  echo "Android App finished"
}

function on_exit() {
  echo "Cleaning up Android Env"
  kill -9 $EMULATOR_PID
  cd ${HARNESS_DIR}
}

PROJ_DIR="hugsnag-android"

trap on_exit EXIT
launch_emulator
build_test_app
launch_test_app
on_exit
