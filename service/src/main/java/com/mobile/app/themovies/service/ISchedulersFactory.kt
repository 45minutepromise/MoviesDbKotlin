package com.mobile.app.themovies.service

import io.reactivex.Scheduler

interface ISchedulersFactory {
    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler
}