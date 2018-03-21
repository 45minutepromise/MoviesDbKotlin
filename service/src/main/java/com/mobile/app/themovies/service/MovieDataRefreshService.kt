package com.mobile.app.themovies.service

import com.mobile.app.themovies.service.interactor.Service
import com.mobile.app.themovies.service.repository.IMoviesRepository
import io.reactivex.Completable
import javax.inject.Inject

class MovieDataRefreshService @Inject constructor(private val moviesRepository: IMoviesRepository,
                                                  private val schedulersFactory: ISchedulersFactory): Service.RxCompletable<Void>() {

    override fun build(params: Void?): Completable = moviesRepository.fetchMovies().subscribeOn(schedulersFactory.io())
}