package com.jmucientes.udacity.bakingapp;

import com.jmucientes.udacity.bakingapp.di.DaggerBakingAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BakingApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerBakingAppComponent.builder().application(this).build();
    }
}