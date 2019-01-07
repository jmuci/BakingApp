package com.jmucientes.udacity.bakingapp.stepdetail.di;

import com.jmucientes.udacity.bakingapp.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StepDetailModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = VideoPlayerModule.class)
    abstract StepDetailFragment stepDetailFragment();

}
