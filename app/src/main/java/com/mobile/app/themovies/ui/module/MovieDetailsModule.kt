package com.mobile.app.themovies.ui.module

import android.arch.lifecycle.ViewModel
import com.mobile.app.themovies.dagger.injection.ViewModelKey
import com.mobile.app.themovies.ui.viewmodel.MovieDetailsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsActivityViewModel::class)
    abstract fun bindMovieDetailsViewModel(viewModel: MovieDetailsActivityViewModel): ViewModel
}