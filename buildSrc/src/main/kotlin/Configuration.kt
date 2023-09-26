@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33

    const val versionName = "1.0"
    const val versionCode = 1
}

object Versions {
    const val kotlin = "1.8.20"
}

object Deps {
    const val appcompat = "androidx.appcompat:appcompat:1.6.1"

    //rx：基于事件流、实现异步操作的库 https://github.com/ReactiveX/RxJava
    const val rxjava3 = "io.reactivex.rxjava3:rxjava:3.1.7"
}