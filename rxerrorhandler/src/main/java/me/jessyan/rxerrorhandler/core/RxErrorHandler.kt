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