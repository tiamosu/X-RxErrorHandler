@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 30

    const val versionName = "1.0"
    const val versionCode = 1
}

object Versions {
    const val kotlin = "1.6.0"
    const val rxjava = "3.1.3"
    const val appcompat = "1.4.0"
}

object Deps {
    //
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    //rx：基于事件流、实现异步操作的库 https://github.com/ReactiveX/RxJava
    const val rxjava3 = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
}