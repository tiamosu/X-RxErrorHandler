package me.jessyan.rxerrorhandler.handler

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
abstract class ErrorHandleSubscriberOfFlowable<T>(private val rxErrorHandler: RxErrorHandler) : Subscriber<T> {

    override fun onSubscribe(s: Subscription?) {}

    override fun onComplete() {}

    override fun onError(t: Throwable?) {
        t?.printStackTrace()
        //如果你某个地方不想使用全局错误处理,则重写 onError(Throwable) 并将 super.onError(e); 删掉
        //如果你不仅想使用全局错误处理,还想加入自己的逻辑,则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
        rxErrorHandler.handlerFactory?.handleError(t)
    }
}