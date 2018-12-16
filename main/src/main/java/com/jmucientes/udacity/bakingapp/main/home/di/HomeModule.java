package com.jmucientes.udacity.bakingapp.main.home.di;

import com.jmucientes.udacity.bakingapp.main.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.main.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

}
