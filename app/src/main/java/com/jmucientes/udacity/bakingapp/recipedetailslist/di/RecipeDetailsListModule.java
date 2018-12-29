package com.jmucientes.udacity.bakingapp.recipedetailslist.di;

import com.jmucientes.udacity.bakingapp.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.recipedetailslist.RecipeDetailListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeDetailsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract RecipeDetailListFragment recipeDetailListFragment();
}
