package me.jessyan.rxerrorhandler.handler

import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.Function
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit

/**
 * ================================================
 * Created by JessYan on 9/22/2017 15:25
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class RetryWithDelayOfFlowable(
        private val maxRetries: Int,
        private val retryDelaySecond: Int
) : Function<Flowable<Throwable?>, Publisher<*>> {
    private val tag = this.javaClass.simpleName
    private var retryCount = 0

    @Throws(Exception::class)
    override fun apply(flowable: @NonNull Flowable<Throwable?>): Publisher<*> {
        return flowable
                .flatMap { throwable ->
                    if (++retryCount <= maxRetries) {
                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                        Log.d(tag, "Flowable get error, it will try after " + retryDelaySecond
                                + " second, retry count " + retryCount)
                        return@flatMap Flowable.timer(retryDelaySecond.toLong(), TimeUnit.SECONDS)
                    }
                    // Max retries hit. Just pass the error along.
                    return@flatMap Flowable.error<Any>(throwable)
                }
    }
}