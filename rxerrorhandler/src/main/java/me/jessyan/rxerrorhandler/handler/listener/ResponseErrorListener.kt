package me.jessyan.rxerrorhandler.handler.listener

/**
 * ================================================
 * Created by JessYan on 9/2/2016 13:58
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
interface ResponseErrorListener {
    fun handleResponseError(t: Throwable?)

    companion object {

        @JvmField
        val EMPTY: ResponseErrorListener = object : ResponseErrorListener {
            override fun handleResponseError(t: Throwable?) {}
        }
    }
}