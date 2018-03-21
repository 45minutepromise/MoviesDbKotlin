package com.mobile.app.themovies.ui.movieslist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobile.app.themovies.service.MoreMoviesService
import com.mobile.app.themovies.service.MoviesService
import com.mobile.app.themovies.service.MovieDataRefreshService
import com.mobile.app.themovies.ui.util.Mapper
import com.mobile.app.themovies.ui.viewmodel.MoviesActivityViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesActivityViewModelTest {
    @Mock lateinit var getMoviesUseCase: MoviesService
    @Mock lateinit var refreshMovieDataUseCase: MovieDataRefreshService
    @Mock lateinit var getMoreMoviesUseCase: MoreMoviesService
    @Mock lateinit var mapper: Mapper

    @Rule @JvmField val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesActivityViewModel

    @Before
    fun setup() {
        viewModel = MoviesActivityViewModel(getMoviesUseCase, refreshMovieDataUseCase,
                getMoreMoviesUseCase, mapper)
    }

    @Test
    fun verifyDataRefreshesOnCreate() {
        verify(refreshMovieDataUseCase).execute(any(), any(), eq(null))
    }

    @Test
    fun verifyViewModelBindsToStream() {
        verify(getMoviesUseCase).execute(any(), any(), eq(null))
    }

    @Test
    fun testLoadMoreViewCallsUseCase() {
        viewModel.loadMoreMovies()

        verify(getMoreMoviesUseCase).execute(any(), any(), eq(2))
    }

}