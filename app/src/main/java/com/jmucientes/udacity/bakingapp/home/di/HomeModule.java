package com.jmucientes.udacity.bakingapp.home.di;

import com.jmucientes.udacity.bakingapp.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

}
