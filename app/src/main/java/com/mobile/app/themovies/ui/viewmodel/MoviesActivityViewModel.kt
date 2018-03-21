package com.mobile.app.themovies.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.mobile.app.themovies.service.MoreMoviesService
import com.mobile.app.themovies.service.MoviesService
import com.mobile.app.themovies.service.MovieDataRefreshService
import com.mobile.app.themovies.ui.util.Mapper
import com.mobile.app.themovies.ui.viewmodel.MovieRowViewModel
import javax.inject.Inject

class MoviesActivityViewModel @Inject constructor(
        private val getMoviesUseCase: MoviesService,
        private val refreshMovieDataUseCase: MovieDataRefreshService,
        private val getMoreMoviesUseCase: MoreMoviesService,
        private val mapper: Mapper): ViewModel() {

    private val liveData = MutableLiveData<List<MovieRowViewModel>>()
    private val moreMoviesLiveData = MutableLiveData<List<MovieRowViewModel>>()
    private var page = 1

    private val TAG = "MoviesViewModel"

    init {
        bindToStream()
        refreshData()
    }

    fun getMoviesLiveData(): LiveData<List<MovieRowViewModel>> = liveData

    fun getMoreMoviesLiveData(): LiveData<List<MovieRowViewModel>> = moreMoviesLiveData

    private fun refreshData()  {
         refreshMovieDataUseCase.execute({}, {error -> Log.e(TAG, error.message)}, null)
    }

    private fun bindToStream(){
        getMoviesUseCase.execute({liveData.postValue(mapper.toRowListItem(it))}, { error -> Log.e(TAG, error.message)}, null)
    }

    fun loadMoreMovies() {
        getMoreMoviesUseCase.execute({moreMoviesLiveData.postValue(mapper.toRowListItem(it))}, { error -> Log.e(TAG, error.message)}, ++page)
    }
}