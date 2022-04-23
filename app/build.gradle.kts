import Dep.android_junit_lib
import Dep.appcompat_lib
import Dep.constraint_layout_lib
import Dep.core_ktx_lib
import Dep.espresso_lib
import Dep.junit_lib
import Dep.material_lib

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = androidCompileSdkVersion

    defaultConfig {
        applicationId = "sajjad.shahbazi.cleanachitecture"
        minSdk = androidMinSdkVersion
        targetSdk = androidTargetSdkVersion
        versionCode = projectVersionCode
        versionName = projectVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = isReleaseMinify
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(core_ktx_lib)
    implementation(appcompat_lib)
    implementation(material_lib)
    implementation(constraint_layout_lib)
    testImplementation(junit_lib)
    androidTestImplementation(android_junit_lib)
    androidTestImplementation(espresso_lib)
}