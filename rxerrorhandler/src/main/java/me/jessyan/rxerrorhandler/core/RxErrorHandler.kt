package me.jessyan.rxerrorhandler.core

import android.content.Context
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
        private var context: Context? = null
        private var responseErrorListener: ResponseErrorListener? = null
        var errorHandlerFactory: ErrorHandlerFactory? = null

        fun with(context: Context): Builder {
            this.context = context
            return this
        }

        fun responseErrorListener(responseErrorListener: ResponseErrorListener?): Builder {
            this.responseErrorListener = responseErrorListener
            return this
        }

        fun build(): RxErrorHandler {
            checkNotNull(context, {
                "Context is required，you should load with(context)"
            })
            checkNotNull(responseErrorListener, {
                "ResponseErrorListener is required，you should load responseErrorListener(responseErrorListener)"
            })

            errorHandlerFactory = ErrorHandlerFactory(context!!, responseErrorListener!!)
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