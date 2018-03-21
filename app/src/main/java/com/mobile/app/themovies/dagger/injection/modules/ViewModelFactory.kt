package com.mobile.app.themovies.dagger.injection.modules

import android.arch.lifecycle.ViewModelProvider
import com.mobile.app.themovies.ui.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelFactory {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}