package me.jessyan.rxerrorhandler.handler

import android.content.Context
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener

/**
 * ================================================
 * Created by JessYan on 9/2/2016 13:47
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class ErrorHandlerFactory(
        private val context: Context,
        private val responseErrorListener: ResponseErrorListener) {

    /**
     * 处理错误
     */
    fun handleError(throwable: Throwable?) {
        responseErrorListener.handleResponseError(context, throwable)
    }
}