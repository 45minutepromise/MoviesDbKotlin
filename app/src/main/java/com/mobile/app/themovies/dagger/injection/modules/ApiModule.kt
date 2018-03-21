package com.mobile.app.themovies.dagger.injection.modules

import com.mobile.app.themovies.data.MoviesService
import com.mobile.app.themovies.repository.MoviesRepository
import com.mobile.app.themovies.service.ISchedulersFactory
import com.mobile.app.themovies.ui.util.SchedulersFactory
import com.mobile.app.themovies.service.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesMoviesService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun providesSchedulersFactory(): ISchedulersFactory = SchedulersFactory()

    @Provides
    @Singleton
    fun providesMoviesRepository(moviesRepository: MoviesRepository): IMoviesRepository = moviesRepository
}