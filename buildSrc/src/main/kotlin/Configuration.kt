@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 15
    const val targetSdkVersion = 30

    const val versionName = "1.0"
    const val versionCode = 1
}

object Versions {
    const val kotlin = "1.4.31"
    const val appcompat = "1.2.0"
    const val rxjava = "3.0.11"
}

object Publish {
    const val groupId = "com.github.tiamosu"
}

object Deps {
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val rxjava3 = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
}