package me.jessyan.rxerrorhandler.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriberOfFlowable
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import me.jessyan.rxerrorhandler.handler.RetryWithDelayOfFlowable

/**
 * ================================================
 * Created by JessYan on 9/2/2016 13:27
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rxErrorHandler = (applicationContext as App).rxErrorHandler
        Observable
                .error<Any>(Exception("Error"))
                .retryWhen(RetryWithDelay(3, 2)) //retry(http connect timeout)
                .subscribe(object : ErrorHandleSubscriber<Any>(rxErrorHandler) {
                    override fun onNext(o: Any) {}
                    override fun onError(t: Throwable) {
                        Log.e("xia", "t1:" + t.message)
                    }
                })

        Flowable //Backpressure
                .error<Any>(Exception("Error"))
                .retryWhen(RetryWithDelayOfFlowable(3, 2)) //retry(http connect timeout)
                .subscribe(object : ErrorHandleSubscriberOfFlowable<Any>(rxErrorHandler) {
                    override fun onNext(o: Any) {}
                    override fun onError(t: Throwable?) {
                        Log.e("xia", "t2:" + t?.message)
                    }
                })
    }
}