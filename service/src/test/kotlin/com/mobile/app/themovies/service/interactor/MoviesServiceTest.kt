package com.mobile.app.themovies.service.interactor

import com.mobile.app.themovies.service.ISchedulersFactory
import com.mobile.app.themovies.service.MoviesService
import com.mobile.app.themovies.service.model.Movie
import com.mobile.app.themovies.service.repository.IMoviesRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesServiceTest {
    @Mock lateinit var moviesRepository: IMoviesRepository
    @Mock lateinit var schedulersFactory: ISchedulersFactory

    private lateinit var getMoviesUseCase: MoviesService
    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        getMoviesUseCase = MoviesService(moviesRepository, schedulersFactory)
        whenever(schedulersFactory.computation()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
    }

    @Test
    fun shouldFetchMoviesFromRepository() {
        //Arrange
        whenever(moviesRepository.getMoviesList()).thenReturn(Flowable.just(listOf(createTestMovie())))

        //Act
        getMoviesUseCase.build(null)

        //Assert
        verify(moviesRepository).getMoviesList()
        verifyNoMoreInteractions(moviesRepository)
    }

    private fun createTestMovie(): Movie {
        return Movie(1L, 10, false, "Thor", 1.3F,
                "", "Thor", "", "Thor Ragnarok",
                "", false)
    }
}