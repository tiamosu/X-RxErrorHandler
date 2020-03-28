# RxErrorHandler

## Error Handle Of RxJava3

## Download

[ ![Download](https://api.bintray.com/packages/weixia/maven/x-rxerrorhandler/images/download.svg) ](https://bintray.com/weixia/maven/x-rxerrorhandler/_latestVersion)

``` gradle
implementation 'me.tiamosu:x-rxerrorhandler:3.0.0'
```

## Initialization

``` kotlin
        val rxErrorHandler = builder()
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
```

## Usage

``` kotlin
        val rxErrorHandler = (applicationContext as App).rxErrorHandler
        Observable
                .error<Any>(Exception("Error"))
                .retryWhen(RetryWithDelay(3, 2)) //retry(http connect timeout)
                .subscribe(object : ErrorHandleSubscriber<Any>(rxErrorHandler) {
                    override fun onNext(o: Any) {}
                    override fun onError(t: Throwable?) {
                        Log.e("xia", "t1:" + t?.message)
                    }
                })

        Flowable //Backpressure
                .error<Any>(Exception("Error"))
                .retryWhen(RetryWithDelayOfFlowable(3, 2)) //retry(http connect timeout)
                .subscribe(object : ErrorHandleSubscriberOfFlowable<Any>(rxErrorHandler) {
                    override fun onNext(o: Any) {}
                    override fun onError(t: Throwable?) {
                        Log.e("xia", "t2:" + t?.message)
                    }
                })
```
