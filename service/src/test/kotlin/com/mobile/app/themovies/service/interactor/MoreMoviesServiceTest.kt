package com.mobile.app.themovies.service.interactor

import com.mobile.app.themovies.service.ISchedulersFactory
import com.mobile.app.themovies.service.MoreMoviesService
import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.repository.IMoviesRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoreMoviesServiceTest {
    @Mock lateinit var moviesRepository: IMoviesRepository
    @Mock lateinit var schedulersFactory: ISchedulersFactory

    private lateinit var getMoreMoviesUseCase: MoreMoviesService
    private val testScheduler = TestScheduler()
    private val page = 2

    @Before
    fun setup() {
        getMoreMoviesUseCase = MoreMoviesService(moviesRepository, schedulersFactory)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(moviesRepository.fetchMoviesPaginated(page)).thenReturn(Single.just(listOf(createMovie())))
    }

    @Test
    fun shouldGetMoreMoviesFromRepository() {
        getMoreMoviesUseCase.build(page)

        //Assert
        verify(moviesRepository).fetchMoviesPaginated(page)
        verifyNoMoreInteractions(moviesRepository)
    }

    private fun createMovie(): Movie {
        return Movie(12L, 10, true, "Minnions", 1.3F,
                "", "Minnions", "", "Minnions",
                "2017-10-10", false)
    }
}