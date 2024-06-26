import deps.compose.androidxComposeCompiler

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        applicationId = "sajjad.shahbazi.cleanachitecture"
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion
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

    composeOptions {
        kotlinCompilerExtensionVersion = androidxComposeCompiler
    }
}

dependencies {
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )
    )

    implementation(domain)
    implementation(common)
    implementation(data)
    implementation(featureUser)
    implementation(conversation)

    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)
    implementation(deps.androidx.constraintLayout)
    implementation(deps.coroutines.core)
    implementation(deps.lifecycle.viewModelKtx)

    implementation(deps.koin.android)
    implementation(deps.koin.core)

    addUnitTest()
    testImplementation(deps.koin.testJunit4)
    testImplementation(deps.koin.test)
    testImplementation(deps.test.junit)

}

kapt {
    correctErrorTypes = true
}