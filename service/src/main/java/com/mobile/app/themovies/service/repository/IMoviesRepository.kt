package com.mobile.app.themovies.service.repository

import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.model.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IMoviesRepository {
    fun fetchMovies(): Completable
    fun getMoviesList(): Flowable<List<Movie>>
    fun fetchMoviesPaginated(page: Int): Single<List<Movie>>
    fun getMovieDetails(id: Long): Single<MovieDetails>
}