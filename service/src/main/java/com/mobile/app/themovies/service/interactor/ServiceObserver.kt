package com.mobile.app.themovies.service.interactor

import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber

class ServiceObserver {
    open class RxSingle<T>: DisposableSingleObserver<T>() {
        override fun onError(e: Throwable) {}
        override fun onSuccess(t: T) {}
    }

    open class RxFlowable<T>: DisposableSubscriber<T>() {
        override fun onError(t: Throwable?) {}
        override fun onComplete() {}
        override fun onNext(t: T) {}
    }
}