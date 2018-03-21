package com.mobile.app.themovies.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.mobile.app.themovies.service.model.MovieDetails
import com.mobile.app.themovies.service.MovieDetailsService
import javax.inject.Inject

class MovieDetailsActivityViewModel @Inject constructor(private val getMovieDetailsUseCase: MovieDetailsService): ViewModel() {

    private val liveData = MutableLiveData<MovieDetails>()

    fun getMovieDetails(id: Long) {
        getMovieDetailsUseCase.execute({ liveData.postValue(it) }, { error -> Log.e("MOVIE_DETAILS", error.message)}, id)
    }

    fun getDetailsLiveData(): LiveData<MovieDetails> = liveData
}