package com.jmucientes.udacity.bakingapp.home.di;

import com.jmucientes.udacity.bakingapp.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

}
