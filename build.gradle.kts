buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")

        //FIREBASE CRASHLYTICS
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath ("com.android.tools.build:gradle:7.2.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.android.library") version "8.1.2" apply false
    //FIREBASE CRASHLYTICS
    id("com.google.firebase.crashlytics") version "2.9.5" apply false
}