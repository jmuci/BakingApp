package com.jmucientes.udacity.bakingapp.recipedetailslist.di;

import com.jmucientes.udacity.bakingapp.recipedetailslist.IngredientsListFragment;
import com.jmucientes.udacity.bakingapp.recipedetailslist.RecipeDetailListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeDetailsListModule {

    @ContributesAndroidInjector
    abstract RecipeDetailListFragment recipeDetailListFragment();

    @ContributesAndroidInjector
    abstract IngredientsListFragment mIngredientsListFragment();
}
