package com.mobile.app.themovies.dagger.injection.modules

import com.mobile.app.themovies.dagger.injection.scope.ActivityScope
import com.mobile.app.themovies.ui.activity.MovieDetailsActivity
import com.mobile.app.themovies.ui.module.MovieDetailsModule
import com.mobile.app.themovies.ui.activity.MoviesActivity
import com.mobile.app.themovies.ui.module.MoviesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(MoviesListModule::class))
    @ActivityScope
    internal abstract fun bindMoviesActivity(): MoviesActivity

    @ContributesAndroidInjector(modules = arrayOf(MovieDetailsModule::class))
    @ActivityScope
    internal abstract fun bindMoviesDetailsActivity(): MovieDetailsActivity
}