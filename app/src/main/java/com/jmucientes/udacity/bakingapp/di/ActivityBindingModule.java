package com.jmucientes.udacity.bakingapp.di;

import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.di.scopes.ActivityScope;
import com.jmucientes.udacity.bakingapp.home.di.HomeModule;
import com.jmucientes.udacity.bakingapp.recipedetailslist.di.RecipeDetailsListModule;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailActivity;
import com.jmucientes.udacity.bakingapp.stepdetail.di.StepDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {
            HomeModule.class,
            RecipeDetailsListModule.class,
    })
    @ActivityScope
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = StepDetailModule.class)
    @ActivityScope
    abstract StepDetailActivity stepDetailActivity();

}
