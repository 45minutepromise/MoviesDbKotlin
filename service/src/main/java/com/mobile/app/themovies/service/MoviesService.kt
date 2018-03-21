package com.mobile.app.themovies.service

import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.interactor.Service
import com.mobile.app.themovies.service.repository.IMoviesRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MoviesService @Inject constructor(private val moviesRepository: IMoviesRepository,
                                        private val schedulersFactory: ISchedulersFactory): Service.RxFlowable<List<Movie>, Void>() {

    override fun build(params: Void?): Flowable<List<Movie>>
            = moviesRepository.getMoviesList()
                .subscribeOn(schedulersFactory.computation()).observeOn(schedulersFactory.main())
}