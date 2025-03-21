// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0") // Use the latest version
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.6.0" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}