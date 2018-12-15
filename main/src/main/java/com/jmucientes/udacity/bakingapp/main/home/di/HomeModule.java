package com.jmucientes.udacity.bakingapp.main.home.di;

import com.jmucientes.udacity.bakingapp.main.di.scopes.ActivityScope;
import com.jmucientes.udacity.bakingapp.main.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.main.home.HomeFragment;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @Provides
    @ActivityScope
    static List<Recipe> recipeListProvider() {
        return new ArrayList<>(Arrays.asList(new Recipe(), new Recipe(), new Recipe()));
    }
}
