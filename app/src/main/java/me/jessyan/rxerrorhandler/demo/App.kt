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

import android.app.Application
import android.net.ParseException
import android.util.Log
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.core.RxErrorHandler.Companion.builder
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener
import org.json.JSONException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * ================================================
 * Created by JessYan on 22/09/2017 15:01
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class App : Application() {
    private val tag = javaClass.simpleName
    lateinit var rxErrorHandler: RxErrorHandler
        private set

    override fun onCreate() {
        super.onCreate()
        //Initialization
        rxErrorHandler = builder()
                .with()
                .responseErrorListener(object : ResponseErrorListener {
                    override fun handleResponseError(t: Throwable?) {
                        if (t is UnknownHostException) {
                            //do something ...
                        } else if (t is SocketTimeoutException) {
                            //do something ...
                        } else if (t is ParseException || t is JSONException) {
                            //do something ...
                        } else {
                            //handle other Exception ...
                        }
                        Log.w(tag, "Error handle")
                    }
                }).build()
    }
}