package com.mobile.app.themovies.service

import com.mobile.app.themovies.service.model.MovieDetails
import com.mobile.app.themovies.service.interactor.Service
import com.mobile.app.themovies.service.repository.IMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailsService @Inject constructor(private val moviesRepository: IMoviesRepository,
                                              private val schedulersFactory: ISchedulersFactory): Service.RxSingle<MovieDetails, Long>() {

    override fun build(params: Long?): Single<MovieDetails> =
            moviesRepository.getMovieDetails(params!!)
                    .subscribeOn(schedulersFactory.computation())
                    .observeOn(schedulersFactory.main())
}