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
package me.jessyan.rxerrorhandler.handler

import io.reactivex.rxjava3.annotations.NonNull
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * ================================================
 * Created by JessYan on 9/22/2017 15:10
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
abstract class ErrorHandleSubscriberOfFlowable<T>(rxErrorHandler: RxErrorHandler) : Subscriber<T> {
    private val handlerFactory: ErrorHandlerFactory? = rxErrorHandler.handlerFactory

    override fun onSubscribe(s: Subscription) {}

    override fun onComplete() {}

    override fun onError(t: @NonNull Throwable?) {
        t?.printStackTrace()
        //如果你某个地方不想使用全局错误处理,则重写 onError(Throwable) 并将 super.onError(e); 删掉
        //如果你不仅想使用全局错误处理,还想加入自己的逻辑,则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
        handlerFactory?.handleError(t)
    }
}