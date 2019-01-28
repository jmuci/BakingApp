package com.jmucientes.udacity.bakingapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jmucientes.udacity.bakingapp.di.qualifiers.ViewModelKey;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;
import com.jmucientes.udacity.bakingapp.home.view.RecipeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel.class)
    @ApplicationScope
    abstract ViewModel bindRecipeViewModel(RecipeViewModel recipeViewModel);

    @Binds
    @ApplicationScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory recipeViewModelFactory);
}
