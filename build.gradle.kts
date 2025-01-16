plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}