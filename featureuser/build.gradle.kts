plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
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
}

dependencies {

    implementation(domain)
    implementation(common)
    implementation(data)

    implementation(deps.lifecycle.viewModelKtx)

    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)
    implementation(deps.androidx.constraintLayout)

    implementation(deps.koin.android)
    implementation(deps.koin.core)
    implementation(deps.lifecycle.viewModelKtx)
    implementation(deps.lifecycle.extensions)
    implementation ("androidx.activity:activity-ktx:1.2.3")

    addUnitTest()
    testImplementation(deps.koin.testJunit4)
    testImplementation(deps.koin.test)
    testImplementation(deps.test.junit)



    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)

    implementation(deps.coroutines.core)
    implementation(deps.timber)

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
}

kapt {
    correctErrorTypes = true
}