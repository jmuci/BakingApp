package com.jmucientes.udacity.bakingapp.di;

import com.jmucientes.udacity.bakingapp.di.scopes.ActivityScope;
import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.main.di.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = HomeModule.class)
    @ActivityScope
    abstract MainActivity mainActivity();
}
