package com.jmucientes.udacity.bakingapp.main.di;

import com.jmucientes.udacity.bakingapp.main.di.scopes.ActivityScope;
import com.jmucientes.udacity.bakingapp.main.MainActivity;
import com.jmucientes.udacity.bakingapp.main.home.di.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = HomeModule.class)
    @ActivityScope
    abstract MainActivity mainActivity();
}
