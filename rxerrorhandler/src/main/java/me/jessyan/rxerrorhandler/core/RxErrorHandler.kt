package me.jessyan.rxerrorhandler.core

import me.jessyan.rxerrorhandler.handler.ErrorHandlerFactory
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener

/**
 * ================================================
 * Created by JessYan on 9/2/2016 13:27
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class RxErrorHandler private constructor(builder: Builder) {
    val handlerFactory: ErrorHandlerFactory? = builder.errorHandlerFactory

    class Builder {
        private var responseErrorListener: ResponseErrorListener? = null
        var errorHandlerFactory: ErrorHandlerFactory? = null

        fun with(): Builder {
            return this
        }

        fun responseErrorListener(responseErrorListener: ResponseErrorListener?): Builder {
            this.responseErrorListener = responseErrorListener
            return this
        }

        fun build(): RxErrorHandler {
            errorHandlerFactory = ErrorHandlerFactory(responseErrorListener)
            return RxErrorHandler(this)
        }
    }

    companion object {

        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }
}