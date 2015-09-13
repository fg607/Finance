#!/bin/bash

gradle clean assembleDebug

cp app/build/outputs/apk/app-debug.apk $HOME/Finance-debug-v$VERSION.apk

gradle clean assembleRelease

ls -lR app/build/outputs

cp app/build/outputs/apk/app-release.apk $HOME/Finance-release-v$VERSION.apk
