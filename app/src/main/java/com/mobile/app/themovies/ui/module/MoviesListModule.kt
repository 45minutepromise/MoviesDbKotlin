package com.mobile.app.themovies.ui.module

import android.arch.lifecycle.ViewModel
import com.mobile.app.themovies.dagger.injection.ViewModelKey
import com.mobile.app.themovies.ui.viewmodel.MoviesActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesListModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesActivityViewModel::class)
    abstract fun bindMoviesActivityViewModel(viewModel: MoviesActivityViewModel): ViewModel
}