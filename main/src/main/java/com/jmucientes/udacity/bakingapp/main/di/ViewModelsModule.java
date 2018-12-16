package com.jmucientes.udacity.bakingapp.main.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jmucientes.udacity.bakingapp.main.di.qualifiers.ViewModelKey;
import com.jmucientes.udacity.bakingapp.main.di.scopes.ApplicationScope;
import com.jmucientes.udacity.bakingapp.main.home.view.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    @ApplicationScope
    abstract ViewModel bindMovieViewModel(HomeViewModel movieDetailViewModel);

    @Binds
    @ApplicationScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory moviesViewModelFactory);
}
