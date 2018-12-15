package com.jmucientes.udacity.bakingapp.main.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BakingAppModule {

    @Binds
    abstract Context bindContext(Application application);
}
