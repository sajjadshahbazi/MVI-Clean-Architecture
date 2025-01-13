
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-android")
}

android {
    namespace = "sajjad.shahbazi.featureuser"
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = deps.compose.androidxComposeCompiler
    }
}

dependencies {
    implementation(domain)
    implementation(common)
    implementation(data)
    implementation(conversation)

    implementation(deps.lifecycle.viewModelKtx)

    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)
    implementation(deps.androidx.constraintLayout)

    implementation(deps.koin.android)
    implementation(deps.koin.core)
    implementation(deps.lifecycle.extensions)

    addUnitTest()
    testImplementation(deps.koin.testJunit4)
    testImplementation(deps.koin.test)
    testImplementation(deps.test.junit)

    implementation(deps.coroutines.core)
    implementation(deps.timber)

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.google.accompanist:accompanist-themeadapter-material:0.28.0")
    implementation("androidx.navigation:navigation-compose:2.7.5")
}

kapt {
    correctErrorTypes = true
}