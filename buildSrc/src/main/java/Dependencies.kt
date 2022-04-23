import Versions.android_junit_version
import Versions.appcompat_version
import Versions.constraint_layout_version
import Versions.core_ktx_version
import Versions.espresso_core_version
import Versions.junit_version
import Versions.material_version

object Versions {
    const val core_ktx_version = "1.7.0"
    const val appcompat_version = "1.4.1"
    const val material_version = "1.5.0"
    const val constraint_layout_version = "2.1.3"
    const val junit_version = "4.13.2"
    const val android_junit_version = "1.1.3"
    const val espresso_core_version = "3.4.0"
}

object Dep {
    const val core_ktx_lib = "androidx.core:core-ktx:$core_ktx_version"
    const val appcompat_lib = "androidx.appcompat:appcompat:$appcompat_version"
    const val material_lib = "com.google.android.material:material:$material_version"
    const val constraint_layout_lib = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"
    const val junit_lib = "junit:junit:$junit_version"
    const val android_junit_lib = "androidx.test.ext:junit:$android_junit_version"
    const val espresso_lib = "androidx.test.espresso:espresso-core:$espresso_core_version"
}
