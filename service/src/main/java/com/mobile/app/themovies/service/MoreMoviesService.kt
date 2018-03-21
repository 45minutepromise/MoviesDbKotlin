package com.mobile.app.themovies.service

import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.interactor.Service
import com.mobile.app.themovies.service.repository.IMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoreMoviesService @Inject constructor(private val moviesRepository: IMoviesRepository,
                                            private val schedulersFactory: ISchedulersFactory): Service.RxSingle<List<Movie>, Int>() {

    override fun build(params: Int?): Single<List<Movie>> {
        return moviesRepository.fetchMoviesPaginated(params ?: 1)
                .subscribeOn(schedulersFactory.io())
                .observeOn(schedulersFactory.main())
    }
}