package me.jessyan.rxerrorhandler.handler

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener

/**
 * ================================================
 * Created by JessYan on 9/2/2016 13:47
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class ErrorHandlerFactory(private val responseErrorListener: ResponseErrorListener?) {

    /**
     * 处理错误
     *
     * @param throwable
     */
    fun handleError(throwable: Throwable?) {
        responseErrorListener?.handleResponseError(throwable)
    }
}