/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
                    override fun onError(t: Throwable?) {
                        Log.e("xia", "t1:" + t?.message)
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