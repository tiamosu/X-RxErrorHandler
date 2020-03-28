package me.jessyan.rxerrorhandler.demo

import android.app.Application
import android.content.Context
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
                .with(this)
                .responseErrorListener(object : ResponseErrorListener {
                    override fun handleResponseError(context: Context, t: Throwable?) {
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