package me.jessyan.rxerrorhandler.handler

import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Function
import java.util.concurrent.TimeUnit

/**
 * ================================================
 * Created by JessYan on 9/2/16 14:32
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class RetryWithDelay(
        private val maxRetries: Int,
        private val retryDelaySecond: Int
) : Function<Observable<Throwable?>, ObservableSource<*>> {
    private val tag = this.javaClass.simpleName
    private var retryCount = 0

    @Throws(Exception::class)
    override fun apply(observable: @NonNull Observable<Throwable?>): ObservableSource<*> {
        return observable
                .flatMap { throwable ->
                    if (++retryCount <= maxRetries) {
                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                        Log.d(tag, "Observable get error, it will try after " + retryDelaySecond
                                + " second, retry count " + retryCount)
                        return@flatMap Observable.timer(retryDelaySecond.toLong(), TimeUnit.SECONDS)
                    }
                    // Max retries hit. Just pass the error along.
                    return@flatMap Observable.error<Any>(throwable)
                }
    }
}