# RxErrorHandler

## Error Handle Of RxJava3

## Download

[ ![Download](https://api.bintray.com/packages/weixia/maven/x-rxerrorhandler/images/download.svg) ](https://bintray.com/weixia/maven/x-rxerrorhandler/_latestVersion)

``` gradle
implementation 'me.tiamosu:x-rxerrorhandler:3.0.0'
```

## Initialization

``` java
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

``` java
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

## About Me
* **Email**: <jess.yan.effort@gmail.com>  
* **Home**: <http://jessyan.me>
* **掘金**: <https://juejin.im/user/57a9dbd9165abd0061714613>
* **简书**: <https://www.jianshu.com/u/1d0c0bc634db>  

## License
```
 Copyright 2016, jessyan               
  
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at   

       http://www.apache.org/licenses/LICENSE-2.0  

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. 
```
