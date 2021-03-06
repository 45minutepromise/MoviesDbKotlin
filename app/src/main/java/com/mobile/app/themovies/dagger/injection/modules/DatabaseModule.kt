package com.mobile.app.themovies.dagger.injection.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.mobile.app.themovies.data.db.MoviesDatabase
import com.mobile.app.themovies.data.db.dao.MovieDetailsDao
import com.mobile.app.themovies.data.db.dao.MoviesDao
import com.mobile.app.themovies.data.db.entities.MovieEntity
import com.mobile.app.themovies.store.MoviesReactiveStore
import com.mobile.app.themovies.data.store.ReactiveStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesMoviesDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies-database").build()
    }

    @Provides
    @Singleton
    fun providesMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }

    @Provides
    @Singleton
    fun providesMoviesDetailsDao(database: MoviesDatabase): MovieDetailsDao {
        return database.moviesDetailsDao()
    }

    @Provides
    @Singleton
    fun providesMoviesReactiveStore(store: MoviesReactiveStore): ReactiveStore<MovieEntity> {
        return store
    }
}