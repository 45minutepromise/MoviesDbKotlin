package com.mobile.app.themovies.ui.util

import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.ui.viewmodel.MovieRowViewModel
import javax.inject.Inject

class Mapper @Inject constructor(){

    fun toRowListItem(movies: List<Movie>): List<MovieRowViewModel> {
        val list = mutableListOf<MovieRowViewModel>()
        movies.forEach {
            list.add(MovieRowViewModel(it.id, it.title, it.poster_path ?: "", it.backdrop_path ?: "", it.release_date, it.vote_count, it.overview))
        }
        return list
    }
}