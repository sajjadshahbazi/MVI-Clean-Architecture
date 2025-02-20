import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

const val kotlinVersion = "1.9.20"

object appConfig {
    const val compileSdkVersion = 34
    const val buildToolsVersion = "32.0.0"

    const val minSdkVersion = 21
    const val targetSdkVersion = 34

    private const val MAJOR = 2
    private const val MINOR = 1
    private const val PATCH = 1
    const val versionCode = MAJOR * 10000 + MINOR * 100 + PATCH
    const val versionName = "$MAJOR.$MINOR.$PATCH-SNAPSHOT"
}

object deps {
    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:1.4.2"
        const val coreKtx = "androidx.core:core-ktx:1.12.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"
        const val material = "com.google.android.material:material:1.6.1"
        const val startup = "androidx.startup:startup-runtime:1.1.1"
    }

    object lifecycle {
        private const val version = "2.5.0"

        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" // viewModelScope
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version" // lifecycleScope
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    }

    object squareup {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

        const val gson = "com.google.code.gson:gson:2.6.2"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:2.1.0"
    }

    object compose {
        const val androidxComposeCompiler = "1.5.4"
        const val bom = "androidx.compose:compose-bom:2023.10.01"

        const val layout = "androidx.compose.foundation:foundation-layout"
        const val foundation = "androidx.compose.foundation:foundation"
        const val ui = "androidx.compose.ui:ui"
        const val material = "androidx.compose.material:material"
        const val material3 = "androidx.compose.material3:material3"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended"
        const val runtime = "androidx.compose.runtime:runtime"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    }

    object coroutines {
        private const val version = "1.6.4"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object koin {
        private const val version = "3.5.0"

        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val testJunit4 = "io.insert-koin:koin-test-junit4:$version"
        const val test = "io.insert-koin:koin-test:$version"
    }

    object hilt{
        private const val version = "2.44"
        const val core = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    const val coil = "io.coil-kt.coil3:coil-compose:3.0.4"
    const val fresco = "com.facebook.fresco:fresco:3.6.0"
    const val viewBindingDelegate = "com.github.hoc081098:ViewBindingDelegate:1.4.0"
    const val flowExt = "io.github.hoc081098:FlowExt:0.5.0"
    const val timber = "com.jakewharton.timber:timber:5.0.1"

    object arrow {
        private const val version = "1.1.3"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object test {
        const val junit = "junit:junit:4.13.2"

        object androidx {
            const val core = "androidx.test:core-ktx:1.4.0"
            const val junit = "androidx.test.ext:junit-ktx:1.1.3"

            object espresso {
                const val core = "androidx.test.espresso:espresso-core:3.4.0"
            }
        }

        const val mockk = "io.mockk:mockk:1.12.4"
        const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"

        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:4.11.0"
        const val koinTest = "io.insert-koin:koin-test:3.4.0"
        const val koinTestJunit = "io.insert-koin:koin-test-junit4:3.4.0"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    }
}


private typealias PDsS = PluginDependenciesSpec
private typealias PDS = PluginDependencySpec

inline val PDsS.androidApplication: PDS get() = id("com.android.application")
inline val PDsS.androidLib: PDS get() = id("com.android.library")
inline val PDsS.kotlinAndroid: PDS get() = id("kotlin-android")
inline val PDsS.kotlin: PDS get() = id("kotlin")
inline val PDsS.kotlinKapt: PDS get() = id("kotlin-kapt")
inline val PDsS.kotlinParcelize: PDS get() = id("kotlin-parcelize")

inline val DependencyHandler.domain get() = project(":domain")
inline val DependencyHandler.common get() = project(":common")
inline val DependencyHandler.data get() = project(":data")
inline val DependencyHandler.companyInfo get() = project(":companyinfo")
inline val DependencyHandler.mviTesting get() = project(":mvi-testing")
inline val DependencyHandler.testUtils get() = project(":test-utils")

fun DependencyHandler.addUnitTest(testImplementation: Boolean = true) {
    val configName = if (testImplementation) "testImplementation" else "implementation"

    add(configName, deps.test.junit)
    add(configName, deps.test.mockk)
    add(configName, deps.test.kotlinJUnit)
    add(configName, deps.coroutines.test)
}