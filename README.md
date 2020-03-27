# RxErrorHandler

## Error Handle Of RxJava3

## Download

[ ![Download](https://api.bintray.com/packages/weixia/maven/x-rxerrorhandler/images/download.svg) ](https://bintray.com/weixia/maven/x-rxerrorhandler/_latestVersion)

``` gradle
implementation 'me.tiamosu:x-rxerrorhandler:3.0.0'
```

## Initialization

``` java
  RxErrorHandler rxErrorHandler = RxErrorHandler 
                .builder()
                .with()
                .responseErrorListener(new ResponseErrorListener() {
                    @Override
                    public void handleResponseError(Throwable t) {
                        if (t instanceof UnknownHostException) {
                            //do something ...
                        } else if (t instanceof SocketTimeoutException) {
                            //do something ...
                        } else {
                            //handle other Exception ...
                        }
                        Log.w(TAG, "Error handle");
                    }
                }).build();
```

## Usage

``` java
  Observable
            .error(new Exception("Error"))
            .retryWhen(new RetryWithDelay(3, 2))//retry(http connect timeout) 
            .subscribe(new ErrorHandleSubscriber<Object>(rxErrorHandler) {
                    @Override
                    public void onNext(Object o) {
                    }
                });

  //Backpressure
  Flowable
          .error(new Exception("Error"))
          .retryWhen(new RetryWithDelayOfFlowable(3, 2))//retry(http connect timeout)
          .subscribe(new ErrorHandleSubscriberOfFlowable<Object>(rxErrorHandler) {
                   @Override
                   public void onNext(Object o) {
                   }
                });
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
