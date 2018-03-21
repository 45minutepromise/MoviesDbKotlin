package com.mobile.app.themovies.data.store

import io.reactivex.Flowable

interface ReactiveStore<T> {
    fun storeAll(objects: List<T>)
    fun  replaceAll(objects: List<T>)
    fun getAll(): Flowable<List<T>>
}