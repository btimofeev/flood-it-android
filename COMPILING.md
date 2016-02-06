### Build release version:

    $ ./gradlew android:assembleRelease

### Sign the app with your private key:

    $ jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore android/build/outputs/apk/android-release-unsigned.apk alias_name

### Verify that your APK is signed:

    $ jarsigner -verify -verbose -certs android/build/outputs/apk/android-release-unsigned.apk

### Align the final APK package using zipalign.

    $ zipalign -v 4 android/build/outputs/apk/android-release-unsigned.apk org.emunix.floodit.apk
