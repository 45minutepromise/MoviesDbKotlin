package com.mobile.app.themovies.repository

import com.mobile.app.themovies.data.MoviesService
import com.mobile.app.themovies.data.db.dao.MovieDetailsDao
import com.mobile.app.themovies.data.db.entities.MovieDetailsEntity
import com.mobile.app.themovies.data.db.entities.MovieEntity
import com.mobile.app.themovies.data.mappers.Mapper
import com.mobile.app.themovies.data.store.ReactiveStore
import com.mobile.app.themovies.service.ISchedulersFactory
import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.model.MovieDetails
import com.mobile.app.themovies.service.repository.IMoviesRepository
import io.reactivex.*
import javax.inject.Inject

class MoviesRepository @Inject constructor(
        private val moviesService: MoviesService,
        private val schedulerFactory: ISchedulersFactory,
        private val movieStore: ReactiveStore<MovieEntity>,
        private val detailsDao: MovieDetailsDao,
        private val mapper: Mapper
): IMoviesRepository {

    override fun getMovieDetails(id: Long): Single<MovieDetails> {
        return detailsDao.getMovieDetails(id)
                .subscribeOn(schedulerFactory.computation())
                .onErrorResumeNext { details(id) }
                .map { mapper.toMovieDetails(it) }
    }

    override fun fetchMovies(): Completable {
        return moviesService.getMovies(1)
                .subscribeOn(schedulerFactory.io())
                .observeOn(schedulerFactory.computation())
                .flatMapObservable { Observable.fromIterable(it.results) }
                .map { mapper.toMovieEntity(it) }
                .toList()
                .doOnSuccess { movieStore.replaceAll(it) }
                .toCompletable()
    }

    override fun getMoviesList(): Flowable<List<Movie>> {
        return movieStore.getAll()
                .map { mapper.toMovieList(it) }

    }

    override fun fetchMoviesPaginated(page: Int): Single<List<Movie>> {
        return moviesService.getMovies(page)
                .flatMapObservable { Observable.fromIterable(it.results) }
                .map { mapper.toMovie(it) }
                .toList()
    }

    private fun details(id: Long): Single<MovieDetailsEntity> {
        return moviesService.getMoveDetails(id)
                .map { mapper.toMovieDetailsEntity(it) }
                .subscribeOn(schedulerFactory.io())
                .doOnSuccess { detailsDao.saveMovieDetails(it) }
    }
}